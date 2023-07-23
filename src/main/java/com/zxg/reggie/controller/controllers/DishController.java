package com.zxg.reggie.controller.controllers;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxg.reggie.common.R;
import com.zxg.reggie.dto.DishDto;
import com.zxg.reggie.dto.TemployeeDto;
import com.zxg.reggie.entity.Category;
import com.zxg.reggie.entity.Dish;
import com.zxg.reggie.entity.Temployee;
import com.zxg.reggie.entity.Tposition;
import com.zxg.reggie.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
            int posId = item.getPosId();
            Tposition tposition = tpositionService.getById(posId);
            String tpositionName = tposition.getName();
            temployeeDto.setPositionname(tpositionName);
            return temployeeDto;
        }).collect(Collectors.toList());
        dishDtoPage.setRecords(list);
        return R.success(dishDtoPage);
    }
    @GetMapping("/{id}")
    public R<DishDto> get(@PathVariable Long id){
        DishDto byIdWithFlavor = dishService.getByIdWithFlavor(id);
        return R.success(byIdWithFlavor);
    }

    @PutMapping
    public R<String> update(@RequestBody TemployeeDto temployeeDto){
//        log.info(dishDto.toString());

        temployeeService.save(temployeeDto);

        return R.success("新增菜品成功");
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
        LambdaUpdateWrapper<Dish> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(ids != null, Dish::getId, ids);
        updateWrapper.set(Dish::getStatus, status);
        dishService.update(updateWrapper);
        return R.success("批量操作成功");
    }
}
