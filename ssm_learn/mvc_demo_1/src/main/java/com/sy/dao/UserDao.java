package com.sy.dao;
import com.sy.domain.User;
import com.sy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;



    public List<User> findAll(){
        List<User> list = jdbcTemplate.query("select * from user",
                new BeanPropertyRowMapper<User>(User.class));

        return list;
    }

    public User findById (int id){
        User user = jdbcTemplate.queryForObject("select * from user where id=?",
                new BeanPropertyRowMapper<User>(User.class),id);
        return user;
    }
    public int updateById(int id,String username,String password){
        int update = jdbcTemplate.update("update user set username=? ,password=? where " +
                        "id=?",
                username,
                password,id);
        return update;
    }
}
