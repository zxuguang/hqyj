package com.zxg.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxg.reggie.dto.SetmealDto;
import com.zxg.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    public void saveWithDish(SetmealDto setmealDto);
    public SetmealDto getByIdWithCatagory(Long id);

    /**
     * 删除套餐，同时删除套餐和菜品的关联数据
     * @param ids
     */
    public void removeWithDish(List<Long> ids);
    public void updateWithDish(SetmealDto setmealDto);
}
