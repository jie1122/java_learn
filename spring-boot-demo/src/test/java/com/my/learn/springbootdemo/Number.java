package com.my.learn.springbootdemo;

import org.junit.jupiter.api.Test;

public class Number {
    /**
     *  java中二进制数字表示
     */
    @Test
    public void bitNum() {
//        在 Java 中，可以使用二进制补码表示法来表示负数。正数和 0 的补码就是该数字本身. 负数的补码是将其对应正数按位取反再加 1。
        System.out.println((byte) 0b10111010);
        System.out.println(Integer.toBinaryString(-70));

    }
}
