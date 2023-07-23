package com.zxg.shixun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxg.shixun.entity.User;
import com.zxg.shixun.mapper.UserMapper;
import com.zxg.shixun.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
