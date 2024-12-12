package com.sb.controller;

import com.sb.service.UserService;


import com.sy.dao.Person;

import com.sy.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserController {
    private Person person;
    private UserDao userDao;

    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) app.getBean("userService");
        userService.saveUser();
        userService.readUser();
    }
}
