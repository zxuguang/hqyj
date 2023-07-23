package com.zxg.shixun.controller.controllers;

import com.zxg.shixun.common.R;
import com.zxg.shixun.entity.Workers;
import com.zxg.shixun.service.WorkersService;
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
