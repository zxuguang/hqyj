package com.zxg.shixun.controller.controllers;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxg.shixun.common.BaseContext;
import com.zxg.shixun.common.R;
import com.zxg.shixun.entity.OrderDetail;
import com.zxg.shixun.entity.Orders;
import com.zxg.shixun.entity.OrdersDto;
import com.zxg.shixun.service.OrderDetailService;
import com.zxg.shixun.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        orderService.submit(orders);
        return R.success("12");
    }
    @PutMapping
    public R<String> changeStatus(@RequestBody Map<String, String> map) {
        int status = Integer.parseInt(map.get("status"));
        Long orderId = Long.valueOf(map.get("id"));
        log.info("修改订单状态:status={status},id={id}", status, orderId);
        LambdaUpdateWrapper<Orders> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Orders::getId, orderId);
        updateWrapper.set(Orders::getStatus, status);
        orderService.update(updateWrapper);
        return R.success("订单状态修改成功");
    }
    @GetMapping("/userPage")
    public R<Page> page(int page, int pageSize) {
        //获取当前id
        Long userId = BaseContext.getCurrentId();
        Page<Orders> pageInfo = new Page<>(page, pageSize);
        Page<OrdersDto> ordersDtoPage = new Page<>(page, pageSize);
        //条件构造器
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        //查询当前用户id订单数据
        queryWrapper.eq(userId != null, Orders::getUserId, userId);
        //按时间降序排序
        queryWrapper.orderByDesc(Orders::getOrderTime);
        orderService.page(pageInfo, queryWrapper);
        List<OrdersDto> list = pageInfo.getRecords().stream().map((item) -> {
            OrdersDto ordersDto = new OrdersDto();
            //获取orderId,然后根据这个id，去orderDetail表中查数据
            Long orderId = item.getId();
            LambdaQueryWrapper<OrderDetail> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(OrderDetail::getOrderId, orderId);
            List<OrderDetail> details = orderDetailService.list(wrapper);
            BeanUtils.copyProperties(item, ordersDto);
            //之后set一下属性
            ordersDto.setOrderDetails(details);
            return ordersDto;
        }).collect(Collectors.toList());
        BeanUtils.copyProperties(pageInfo, ordersDtoPage, "records");
        ordersDtoPage.setRecords(list);
        //日志输出看一下
        log.info("list:{}", list);
        return R.success(ordersDtoPage);
    }
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String number,String beginTime,String endTime){
        //分页构造器对象
        Page<Orders> pageInfo = new Page<>(page,pageSize);
        //构造条件查询对象
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();

        //添加查询条件  动态sql  字符串使用StringUtils.isNotEmpty这个方法来判断
        //这里使用了范围查询的动态SQL，这里是重点！！！
        queryWrapper.like(number!=null,Orders::getNumber,number)
                .gt(StringUtils.isNotEmpty(beginTime),Orders::getOrderTime,beginTime)
                .lt(StringUtils.isNotEmpty(endTime),Orders::getOrderTime,endTime);

        orderService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }


//    @GetMapping("/userPage")
//    public R<Page> userPage(int page, int pageSize) {
//        //获取当前id
//        Long userId = BaseContext.getCurrentId();
//        Page<Orders> pageInfo = new Page<>(page, pageSize);
//        Page<OrdersDto> ordersDtoPage = new Page<>(page, pageSize);
//        //条件构造器
//        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
//        //查询当前用户id订单数据
//        queryWrapper.eq(userId != null, Orders::getUserId, userId);
//        //按时间降序排序
//        queryWrapper.orderByDesc(Orders::getOrderTime);
//        orderService.page(pageInfo, queryWrapper);
//        List<OrdersDto> list = pageInfo.getRecords().stream().map((item) -> {
//            OrdersDto ordersDto = new OrdersDto();
//            //获取orderId,然后根据这个id，去orderDetail表中查数据
//            Long orderId = item.getId();
//            LambdaQueryWrapper<OrderDetail> wrapper = new LambdaQueryWrapper<>();
//            wrapper.eq(OrderDetail::getOrderId, orderId);
//            List<OrderDetail> details = orderDetailService.list(wrapper);
//            BeanUtils.copyProperties(item, ordersDto);
//            //之后set一下属性
//            ordersDto.setOrderDetails(details);
//            return ordersDto;
//        }).collect(Collectors.toList());
//        BeanUtils.copyProperties(pageInfo, ordersDtoPage, "records");
//        ordersDtoPage.setRecords(list);
//        //日志输出看一下
//        log.info("list:{}", list);
//        return R.success(ordersDtoPage);
//    }
}
