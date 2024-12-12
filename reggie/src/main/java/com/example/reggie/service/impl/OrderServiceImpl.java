package com.example.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.reggie.common.MyException;
import com.example.reggie.common.MyThreadContext;
import com.example.reggie.domain.*;
import com.example.reggie.mapper.OrderMapper;


import com.example.reggie.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {
    @Autowired
    private UserService userService;
    @Autowired
    private AddressBookService addressBookService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Override
    public void submit(Orders orders) {
        Long userId = MyThreadContext.getValue();
//        查询用户
        User user = userService.getById(userId);
//        获取地址数据
        Long addressBookId = orders.getAddressBookId();
        AddressBook addressBook = addressBookService.getById(addressBookId);

//        查询用户购物车
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, userId);
        List<ShoppingCart> shoppingCartList = shoppingCartService.list(queryWrapper);
        if (shoppingCartList == null) {
            throw new MyException("购物车为空");
        }
        Long orderId = IdWorker.getId();

        AtomicInteger amount = new AtomicInteger(0);
        List<OrderDetail> orderDetails = new ArrayList<>();
        shoppingCartList.forEach(shoppingCart ->{
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setNumber(shoppingCart.getNumber());
            orderDetail.setDishId(shoppingCart.getDishId());
            orderDetail.setDishFlavor(shoppingCart.getDishFlavor());
            orderDetail.setSetmealId(shoppingCart.getSetmealId());
            orderDetail.setName(shoppingCart.getName());
            orderDetail.setAmount(shoppingCart.getAmount());
            orderDetail.setImage(shoppingCart.getImage());

//            一种商品总金额
            BigDecimal shoppingCartSum = shoppingCart.getAmount().multiply(BigDecimal.valueOf(shoppingCart.getNumber()));
//            累加
            amount.addAndGet(shoppingCartSum.intValue());
            orderDetails.add(orderDetail);
        });

//        向订单表插入数据
        orders.setId(orderId);
        orders.setNumber(String.valueOf(orderId));
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setStatus(2);
        orders.setUserId(userId);
        orders.setUserName(user.getName());
        orders.setPhone(addressBook.getPhone());
        orders.setConsignee(addressBook.getConsignee());
        orders.setAmount(BigDecimal.valueOf(amount.get()));
        this.save(orders);
//        向订单明细表插入数据
        orderDetailService.saveBatch(orderDetails);

//        清空购物车数据
        shoppingCartService.remove(queryWrapper);

    }
}
