package com.zxg.reggie.controller.controllers;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxg.reggie.common.R;
import com.zxg.reggie.dto.SetmealDto;
import com.zxg.reggie.entity.Category;
import com.zxg.reggie.entity.Setmeal;
import com.zxg.reggie.service.CategoryService;
import com.zxg.reggie.service.SetmealDishService;
import com.zxg.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SetmealDishService setmealDishService;

    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto) {
        setmealService.saveWithDish(setmealDto);
        return R.success("成功");
    }

    @GetMapping("/page")
    public R<Page> save(int page, int pageSize, String name) {
        Page<Setmeal> setmealPage = new Page<>(page, pageSize);
        Page<SetmealDto> setmealDtoPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(name != null, Setmeal::getName, name);
        lambdaQueryWrapper.orderByAsc(Setmeal::getCreateTime);
        setmealService.page(setmealPage, lambdaQueryWrapper);
        BeanUtils.copyProperties(setmealPage, setmealDtoPage, "records");
        List<Setmeal> records = setmealPage.getRecords();
        List<SetmealDto> records1 = records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item, setmealDto);
            Long categoryId = item.getCategoryId();
            Category byId = categoryService.getById(categoryId);
            String name1 = byId.getName();
            setmealDto.setCategoryName(name1);
            return setmealDto;
        }).collect(Collectors.toList());
        setmealDtoPage.setRecords(records1);
        return R.success(setmealDtoPage);
    }

    @GetMapping("/{id}")
    public R<SetmealDto> get(@PathVariable Long id) {
        SetmealDto byIdWithCatagory = setmealService.getByIdWithCatagory(id);
        return R.success(byIdWithCatagory);
    }

    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {
        log.info("",ids);
        setmealService.removeWithDish(ids);
        return R.success("套餐删除成功");
    }
    @PutMapping
    public R<String> updateWithSetmeal(@RequestBody SetmealDto setmealDto){
        setmealService.updateWithDish(setmealDto);
        return  R.success("成功");
    }
    @PostMapping("/status/{status}")
    public R<String> status(@PathVariable String status,@RequestParam List<Long> ids){
        LambdaUpdateWrapper<Setmeal> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(Setmeal::getId, ids);
        updateWrapper.set(Setmeal::getStatus, status);
        setmealService.update(updateWrapper);
        return R.success("批量操作成功");
    }
    @GetMapping("/list")
    public R<List<Setmeal>> list(Setmeal setmeal) {
        //条件构造器
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        queryWrapper.eq(setmeal.getCategoryId() != null, Setmeal::getCategoryId, setmeal.getCategoryId());
        queryWrapper.eq(setmeal.getStatus() != null, Setmeal::getStatus, 1);
        //排序
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        List<Setmeal> setmealList = setmealService.list(queryWrapper);
        return R.success(setmealList);
    }
 }
