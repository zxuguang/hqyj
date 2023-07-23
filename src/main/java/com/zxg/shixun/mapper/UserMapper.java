package com.zxg.shixun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxg.shixun.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
