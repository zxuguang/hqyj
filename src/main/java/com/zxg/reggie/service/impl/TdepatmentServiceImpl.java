package com.zxg.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxg.reggie.entity.Tdepartment;
import com.zxg.reggie.mapper.TdepartmentMapper;
import com.zxg.reggie.service.TdepartmentService;
import org.springframework.stereotype.Service;

@Service
public class TdepatmentServiceImpl extends ServiceImpl<TdepartmentMapper, Tdepartment> implements TdepartmentService {
}
