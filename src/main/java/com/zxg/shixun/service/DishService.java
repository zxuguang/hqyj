package com.zxg.shixun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxg.shixun.dto.DishDto;
import com.zxg.shixun.entity.Dish;

import java.util.List;

public interface DishService extends IService<Dish> {
    public void saveWithFlavor(DishDto dishDto);
    public DishDto getByIdWithFlavor(Long id);
    public void updateWithFlavor(DishDto dishDto);
    public void deleteWith(List<Long> id);
    public void setStatus(Integer idid,Long ids);
}
