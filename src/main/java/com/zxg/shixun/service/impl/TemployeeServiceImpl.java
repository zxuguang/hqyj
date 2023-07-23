package com.zxg.shixun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxg.shixun.entity.Temployee;
import com.zxg.shixun.mapper.TemployeeMapper;
import com.zxg.shixun.service.TemployeeService;
import org.springframework.stereotype.Service;

@Service
public class TemployeeServiceImpl extends ServiceImpl<TemployeeMapper,Temployee> implements TemployeeService {
}
