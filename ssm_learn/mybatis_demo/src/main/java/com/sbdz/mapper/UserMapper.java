package com.sbdz.mapper;

import com.sbdz.domain.User;

import java.util.List;

public interface UserMapper {
    public List<User> findById(int id);
    public List<User> findAll();
    public List<User> findByFields(User user);
    public List<User> findByIds(List<Integer> ids);
}
