package com.zxg.shixun.controller.controllers;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxg.shixun.common.R;
import com.zxg.shixun.dto.DishDto;
import com.zxg.shixun.dto.TemployeeDto;
import com.zxg.shixun.entity.Dish;
import com.zxg.shixun.entity.Tdepartment;
import com.zxg.shixun.entity.Temployee;
import com.zxg.shixun.entity.Tposition;
import com.zxg.shixun.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private DishFlavorService dishFlavorService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TemployeeService temployeeService;
    @Autowired
    private TpositionService tpositionService;
    @Autowired
    private TdepartmentService tdepartmentService;
    @PostMapping
    public R<String> post(@RequestBody TemployeeDto temployeeDto){
//    dishService.saveWithFlavor(dishDto);
        temployeeService.save(temployeeDto);
    return R.success("chenggong");
    }
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        Page<Temployee> pageinfo = new Page<>(page,pageSize);
        Page<TemployeeDto> dishDtoPage = new Page<>();
        LambdaQueryWrapper<Temployee> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(name!=null,Temployee::getName,name);
        temployeeService.page(pageinfo,lambdaQueryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageinfo,dishDtoPage,"records ");
        List<Temployee> records = pageinfo.getRecords();
        List<TemployeeDto> list = records.stream().map((item)->{
            TemployeeDto temployeeDto = new TemployeeDto();
            BeanUtils.copyProperties(item,temployeeDto);
            Long posId = item.getPosId();
            Long departmentId = item.getDepartmentId();
            Tposition tposition = tpositionService.getById(posId);
            Tdepartment tdepartmentServiceById = tdepartmentService.getById(departmentId);
            String tpositionName = tposition.getName();
            String tdepartmentName = tdepartmentServiceById.getName();
            temployeeDto.setPositionname(tpositionName);
            temployeeDto.setDepartnema(tdepartmentName);
            return temployeeDto;
        }).collect(Collectors.toList());
        dishDtoPage.setRecords(list);
        return R.success(dishDtoPage);
    }
    @GetMapping("/{id}")
    public R<Temployee> get(@PathVariable Long id){
        Temployee temployee = temployeeService.getById(id);
        return R.success(temployee);
    }

    @PutMapping
    public R<String> update(@RequestBody TemployeeDto temployeeDto){
//        log.info(dishDto.toString());

        temployeeService.updateById(temployeeDto);

        return R.success("新增成功");
    }
    @GetMapping("/list")
    public R<List<Dish>> list(Dish dish){
        Long id = dish.getCategoryId();
        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(id!=null,Dish::getCategoryId,id);
        lambdaQueryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getCreateTime);
        List<Dish> list = dishService.list(lambdaQueryWrapper);
        return R.success(list);
    }
    @DeleteMapping
    public R<String> deleteWith(@RequestParam List<Long> ids){
        temployeeService.removeByIds(ids);
        return R.success("chenggong");
    }
    @PostMapping("/status/{status}")
    public R<String> status(@PathVariable Integer status, @RequestParam List<Long> ids) {
        log.info("status:{},ids:{}", status, ids);
        LambdaUpdateWrapper<Temployee> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(ids != null, Temployee::getId, ids);
        updateWrapper.set(Temployee::getStatus, status);
        temployeeService.update(updateWrapper);
        return R.success("批量操作成功");
    }
}
