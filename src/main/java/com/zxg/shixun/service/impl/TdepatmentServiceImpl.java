package com.zxg.shixun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxg.shixun.entity.Tdepartment;
import com.zxg.shixun.mapper.TdepartmentMapper;
import com.zxg.shixun.service.TdepartmentService;
import org.springframework.stereotype.Service;

@Service
public class TdepatmentServiceImpl extends ServiceImpl<TdepartmentMapper, Tdepartment> implements TdepartmentService {
}
