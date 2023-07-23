package com.zxg.shixun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxg.shixun.common.CustomException;
import com.zxg.shixun.dto.SetmealDto;
import com.zxg.shixun.entity.Setmeal;
import com.zxg.shixun.entity.SetmealDish;
import com.zxg.shixun.mapper.SetmealMapper;
import com.zxg.shixun.service.SetmealDishService;
import com.zxg.shixun.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper,Setmeal> implements SetmealService {
    @Autowired
    SetmealDishService setmealDishService;
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        //保存套餐的基本信息
        this.save(setmealDto);
        //保存套餐的关联信息
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item)->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(setmealDishes);
    }



    @Override
    @Transactional
    public void removeWithDish(List<Long> ids) {
        //查询套餐状态
        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.in(Setmeal::getId,ids);
        lambdaQueryWrapper.eq(Setmeal::getStatus,1);
        int count = this.count(lambdaQueryWrapper);
        //如果不能删除，跳出一个业务异常
        if(count>0){
            throw new CustomException("套餐正在售卖中，不可删除");
        }
        //若果可以删，先删除套餐表的
        this.removeByIds(ids);
        //在删除套餐关系标的
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper1 = new LambdaQueryWrapper();
        lambdaQueryWrapper1.in(SetmealDish::getSetmealId,ids);
        setmealDishService.remove(lambdaQueryWrapper1);
    }
    @Override
    public SetmealDto getByIdWithCatagory(Long id) {
        Setmeal setmeal = this.getById(id);
        SetmealDto setmealDto = new SetmealDto();
        BeanUtils.copyProperties(setmeal,setmealDto);
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(SetmealDish::getSetmealId,setmeal.getId());
        List<SetmealDish> list = setmealDishService.list(lambdaQueryWrapper);
        setmealDto.setSetmealDishes(list);
        return setmealDto;
    }
    @Override
    public void updateWithDish(SetmealDto setmealDto) {
        //更新基本信息
        this.updateById(setmealDto);
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(SetmealDish::getSetmealId,setmealDto.getId());
        setmealDishService.remove(lambdaQueryWrapper);
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        List<SetmealDish> setmealDishes1 = setmealDishes.stream().map((item)->{
        item.setSetmealId(setmealDto.getId());
        return item;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(setmealDishes1);
    }

}
