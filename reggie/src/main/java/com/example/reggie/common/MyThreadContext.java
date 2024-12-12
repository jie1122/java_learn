package com.example.reggie.common;

public class MyThreadContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setValue(Long empId){
        threadLocal.set(empId);
    }
    public static Long getValue(){
        return threadLocal.get();
    }
}
