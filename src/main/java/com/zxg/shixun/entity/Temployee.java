package com.zxg.shixun.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Temployee implements Serializable {
    private Long id;
    private String name;
    private String gender;
    private Date birthday;
    private String email;
    private String phone;
    private String address;
    private Long departmentId;
    private Long posId;
    private String image;
    private Boolean status;
}
