package com.zxg.reggie.controller.controllers;

import com.zxg.reggie.common.R;
import com.zxg.reggie.entity.Category;
import com.zxg.reggie.entity.Workers;
import com.zxg.reggie.service.WorkersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workers")
@Slf4j
public class WorkersController {
    @Autowired
    private WorkersService workersService;
    @PostMapping
    public R<String> save(@RequestBody Workers workers){
        workersService.save(workers);
        return R.success("新增职位成功");
    }

}
