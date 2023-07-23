package com.zxg.shixun.dto;

import com.zxg.shixun.entity.Temployee;
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
