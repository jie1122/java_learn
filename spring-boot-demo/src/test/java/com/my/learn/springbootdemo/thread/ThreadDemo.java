package com.my.learn.springbootdemo.thread;


import org.junit.jupiter.api.Test;

public class ThreadDemo {
    @Test
    public void demo(){
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
