package com.zxg.shixun.dto;

import com.zxg.shixun.entity.Setmeal;
import com.zxg.shixun.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
