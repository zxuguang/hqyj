package com.zxg.shixun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxg.shixun.common.CustomException;
import com.zxg.shixun.dto.DishDto;
import com.zxg.shixun.entity.Dish;
import com.zxg.shixun.entity.DishFlavor;
import com.zxg.shixun.mapper.DishMapper;
import com.zxg.shixun.service.DishFlavorService;
import com.zxg.shixun.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper,Dish> implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;
    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        /**
         * 新增菜品，同时保存口味数据
         */
        this.save(dishDto);
//        Long id = dishDto.getId();
//        List<DishFlavor> flavors = dishDto.getFlavors();
//
//        flavors = flavors.stream().map((item)->{
//            item.setDishId(id);
//            return item;
//        }).collect(Collectors.toList());
//        //保存菜品口味到口味表
//        dishFlavorService.saveBatch(flavors);
}

    @Override
    public DishDto getByIdWithFlavor(Long id) {
//        查询菜品基本信息
        Dish dish = this.getById(id);
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);
//        查询口味信息，从dishflavor表查询
        LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(DishFlavor::getDishId,dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(lambdaQueryWrapper);
        dishDto.setFlavors(flavors);
        return dishDto;
    }

    @Override
    public void updateWithFlavor(DishDto dishDto) {

        this.updateById(dishDto);

        LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(lambdaQueryWrapper);

        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item)->{
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);

    }

    @Override
    @Transactional
    public void deleteWith(List<Long> ids) {
        //判断是否在售
        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.in(Dish::getId,ids);
        lambdaQueryWrapper.eq(Dish::getStatus,1);
        //如果在手抛出异常

        int count = this.count(lambdaQueryWrapper);
        if(count>0){
            throw new CustomException("删除失败");
        }
        //删除菜品表
        this.removeByIds(ids);
        //删除
        LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper1 = new LambdaQueryWrapper();
        lambdaQueryWrapper1.in(DishFlavor::getDishId,ids);
        dishFlavorService.remove(lambdaQueryWrapper1);
    }

    @Override
    public void setStatus(Integer idid, Long ids) {
        Dish byId = this.getById(ids);
        byId.setStatus(idid);
        this.updateById(byId);
    }


}
