package com.zxg.reggie.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
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
    private int departmentId;
    private int posId;
    private String image;
    private Boolean status;
}
