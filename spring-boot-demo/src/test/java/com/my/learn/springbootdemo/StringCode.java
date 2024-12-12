package com.my.learn.springbootdemo;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class StringCode {
    private byte [] bytes = {97, 98, 99, -28, -72, -128, -28, -70, -116, -28, -72, -119};
    private byte [] bytes1 = {97, 98, 99, -46, -69, -74, -2, -56, -3};
    @Test
    public void encodeTest() throws UnsupportedEncodingException {
        /*
         编码
         String
         public byte[] getBytes

         */
        String str = "abc一二三";

        bytes = str.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(bytes));

        bytes1 = str.getBytes("gbk");
        System.out.println(Arrays.toString(bytes1));
    }

    @Test
    public void uncodeTest() throws UnsupportedEncodingException {
        /*
         解码
         new String ()
         */
        String str = new String(bytes,"utf-8");
        System.out.println(str);

        String str1 = new String(bytes1,"gbk");
        System.out.println(str1);

    }

}
