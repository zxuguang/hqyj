package com.zxg.shixun.controller.controllers;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxg.shixun.common.R;
import com.zxg.shixun.entity.Category;
import com.zxg.shixun.entity.Tdepartment;
import com.zxg.shixun.entity.Tposition;
import com.zxg.shixun.service.CategoryService;
import com.zxg.shixun.service.TdepartmentService;
import com.zxg.shixun.service.TpositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    TpositionService tpositionService;
    @Autowired
    TdepartmentService tdepartmentService;
    @PostMapping
    public R<String> save(@RequestBody Category category){
        categoryService.save(category);
        return R.success("新增分类成功");
    }
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize){
//        1.构造分页构造器
        Page pageInfo = new Page(page,pageSize);
//        2.构造条件
        LambdaQueryWrapper<Tdepartment> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.orderByDesc(Tdepartment::getId);
        tdepartmentService.page(pageInfo,lambdaQueryWrapper);
        return R.success(pageInfo);
    }
    @GetMapping("/page1")
    public R<Page> page1(int page,int pageSize){
//        1.构造分页构造器
        Page pageInfo = new Page(page,pageSize);
//        2.构造条件
        LambdaQueryWrapper<Tposition> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.orderByDesc(Tposition::getId);
        tpositionService.page(pageInfo,lambdaQueryWrapper);
        return R.success(pageInfo);
    }
    @DeleteMapping
    public R<String> delete(Long ids){
//        categoryService.removeById(ids);
        tdepartmentService.removeById(ids);
        return R.success("删除成功");
    }
    @DeleteMapping("/delete")
    public R<String> delete1(Long ids){
//        categoryService.removeById(ids);
        tpositionService.removeById(ids);
        return R.success("删除成功");
    }
    @PutMapping
    public R<String> update(@RequestBody Tdepartment tdepartment){
        tdepartmentService.updateById(tdepartment);
        return R.success("修改成功");
    }
    @PutMapping("/edit")
    public R<String> update1(@RequestBody Tposition tposition){
        tpositionService.updateById(tposition);
        return R.success("修改成功");
    }
    @GetMapping("/list")
    public R<List<Tposition>> list(Tposition tposition){
        LambdaQueryWrapper<Tposition> lambdaQueryWrapper1 = new LambdaQueryWrapper();
        lambdaQueryWrapper1.eq(tposition.getId()!=null,Tposition::getId,tposition.getId());
        List<Tposition> list = tpositionService.list(lambdaQueryWrapper1);

        return R.success(list);
    }
    @GetMapping("/list1")
    public R<List<Tdepartment>> list1(Tdepartment tdepartment){
        LambdaQueryWrapper<Tdepartment> lambdaQueryWrapper1 = new LambdaQueryWrapper();
        lambdaQueryWrapper1.eq(tdepartment.getId()!=null,Tdepartment::getId,tdepartment.getId());
        List<Tdepartment> list1 = tdepartmentService.list(lambdaQueryWrapper1);

        return R.success(list1);

    }
}
