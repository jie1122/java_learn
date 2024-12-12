package com.my.learn.springbootdemo;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class MapTest {
    @Test
    public void   mapTest(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("ab", "123");
        System.out.println(hashMap);
        System.out.println(hashMap.get("dd"));
        System.out.println(hashMap.containsKey("dd"));
    }
}
