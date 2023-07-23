package com.zxg.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxg.reggie.common.CustomException;
import com.zxg.reggie.entity.Category;
import com.zxg.reggie.entity.Dish;
import com.zxg.reggie.entity.Setmeal;
import com.zxg.reggie.entity.SetmealDish;
import com.zxg.reggie.mapper.CategoryMapper;
import com.zxg.reggie.service.CategoryService;
import com.zxg.reggie.service.DishService;
import com.zxg.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    DishService dishService;
    @Autowired
    SetmealService setmealService;
    /**
     * 根据id进行删除，删除之前进行判断
     * @param id
     */
    @Override
    public void remove(Long id) {
        //查询当前是否关联了菜品，若是关联，抛出一个异常
        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count = dishService.count(lambdaQueryWrapper);
        if(count>0){
            throw new CustomException("关联了菜品，不可删除");
        }
        //查询当前是否关联了套餐，若是关联，抛出一个异常
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Setmeal::getCategoryId,id);
        int count1 = setmealService.count(queryWrapper);
        if(count1>0){
            throw new CustomException("关联了套餐，不可删除");

        }
//        正常删除
        super.removeById(id);
    }
    }
