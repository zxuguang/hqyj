package com.zxg.shixun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxg.shixun.entity.Workers;
import com.zxg.shixun.mapper.WorkersMapper;
import com.zxg.shixun.service.WorkersService;
import org.springframework.stereotype.Service;

@Service
public class WorkersServiceImpl extends ServiceImpl<WorkersMapper, Workers> implements WorkersService {
}
