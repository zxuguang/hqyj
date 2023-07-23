package com.zxg.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxg.reggie.entity.Orders;

public interface OrderService extends IService<Orders> {
    void submit(Orders orders);
}
