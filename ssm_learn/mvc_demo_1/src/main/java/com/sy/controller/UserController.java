package com.sy.controller;
import com.sy.domain.User;
import com.sy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("getAll")
    @ResponseBody
    public List<User> getAll(){
        List<User> userList = userService.list();
        return userList;

    }
    @RequestMapping("getById")
    @ResponseBody
    public User getById(int id){
        User user = userService.findById(id);
        return user;
    }

    @RequestMapping("editById")
    @ResponseBody
    public User editById(@RequestParam("id")int id,String username,String password){
        User user = userService.updateById(id, username, password);
        return user;
    }
}
