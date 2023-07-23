package com.zxg.shixun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxg.shixun.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
