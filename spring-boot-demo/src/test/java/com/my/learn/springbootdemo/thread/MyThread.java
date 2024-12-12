package com.my.learn.springbootdemo.thread;

public class MyThread extends Thread{
    @Override
    public void run(){
        System.out.println(getName() + "thread");
    }
}
