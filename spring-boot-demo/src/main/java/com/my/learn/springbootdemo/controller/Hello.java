package com.my.learn.springbootdemo.controller;

import com.my.learn.springbootdemo.domain.User;
import com.my.learn.springbootdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class Hello {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/")
    public List<User> hello(){
        List<User> allUser = userMapper.getAllUser();
        System.out.println(allUser);
        return allUser;
    }
}
 