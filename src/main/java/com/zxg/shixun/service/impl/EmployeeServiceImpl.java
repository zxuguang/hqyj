package com.zxg.shixun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxg.shixun.entity.Employee;
import com.zxg.shixun.mapper.EmployeeMapper;
import com.zxg.shixun.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
