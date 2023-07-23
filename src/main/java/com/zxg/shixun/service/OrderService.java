package com.zxg.shixun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxg.shixun.entity.Orders;

public interface OrderService extends IService<Orders> {
    void submit(Orders orders);
}
