package com.zxg.reggie.dto;

import com.zxg.reggie.entity.DishFlavor;
import com.zxg.reggie.entity.Temployee;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class TemployeeDto extends Temployee {
    private List<Temployee> flavors = new ArrayList<>();

    private String positionname;
    private String departnema;
//    private String

}
