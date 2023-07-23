package com.zxg.shixun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxg.shixun.entity.OrderDetail;
import com.zxg.shixun.mapper.OrderDetailMapper;
import com.zxg.shixun.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
