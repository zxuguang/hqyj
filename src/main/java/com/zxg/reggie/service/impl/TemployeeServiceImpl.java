package com.zxg.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxg.reggie.entity.Temployee;
import com.zxg.reggie.mapper.TemployeeMapper;
import com.zxg.reggie.service.TemployeeService;
import org.springframework.stereotype.Service;

@Service
public class TemployeeServiceImpl extends ServiceImpl<TemployeeMapper,Temployee> implements TemployeeService {
}
