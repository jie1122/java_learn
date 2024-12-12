package com.sy.service;
import com.sy.domain.User;
import com.sy.dao.UserDao;
import com.sy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMapper userMapper;

    public List<User> list(){
        List<User> userList = userMapper.findAll();
//        List<User> userList = userDao.findAll();

        return userList;
    }

    public User findById(int id){
        User user = userDao.findById(id);
        return user;
    }
    public User updateById(int id ,String username, String password){
        int i = userDao.updateById(id, username, password);

        User user = userDao.findById(id);
        return  user;
    }

}
