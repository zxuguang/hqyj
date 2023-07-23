package com.zxg.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxg.reggie.entity.ShoppingCart;
import com.zxg.reggie.mapper.ShoppingCartMapper;
import com.zxg.reggie.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper,ShoppingCart> implements ShoppingCartService {
}
