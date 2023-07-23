package com.zxg.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxg.reggie.entity.Workers;
import com.zxg.reggie.mapper.WorkersMapper;
import com.zxg.reggie.service.WorkersService;
import org.springframework.stereotype.Service;

@Service
public class WorkersServiceImpl extends ServiceImpl<WorkersMapper, Workers> implements WorkersService {
}
