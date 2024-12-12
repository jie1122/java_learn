package com.example.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.reggie.domain.Orders;


public interface OrderService extends IService<Orders> {
    void submit(Orders orders);
}
