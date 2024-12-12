package com.sy.mapper;

import com.sy.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    public List<User> findById(int id);
    public List<User> findAll();
    public List<User> findByFields(User user);
    public List<User> findByIds(List<Integer> ids);
}
