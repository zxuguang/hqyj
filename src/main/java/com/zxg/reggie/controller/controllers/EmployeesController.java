package com.zxg.reggie.controller.controllers;

import com.zxg.reggie.service.TemployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    TemployeeService temployeeService;

}
