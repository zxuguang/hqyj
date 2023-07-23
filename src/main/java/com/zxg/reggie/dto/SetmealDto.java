package com.zxg.reggie.dto;

import com.zxg.reggie.entity.Setmeal;
import com.zxg.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
