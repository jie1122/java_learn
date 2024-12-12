package com.sb.service;


import com.sb.dao.ReadUser;
import com.sb.dao.SaveUser;


public class UserService {
    private SaveUser saveUser;
    private ReadUser readUser;

    public UserService(ReadUser readUser) {
        this.readUser = readUser;
    }

    public UserService() {
    }

    public void setSaveUser(SaveUser saveUser) {
        this.saveUser = saveUser;
    }

    public void saveUser(){
        saveUser.save();
    }
    public void readUser(){
        readUser.read();
    }

    public void init(){
        System.out.println("init......");
    }


}
