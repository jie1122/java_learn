package com.my.learn.springbootdemo.mapper;

import com.my.learn.springbootdemo.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {
    public List<User> getAllUser();
}
