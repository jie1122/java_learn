package com.example.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.reggie.domain.OrderDetail;
import com.example.reggie.mapper.OrderDetailMapper;



import com.example.reggie.service.OrderDetailService;

import org.springframework.stereotype.Service;


@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
