package com.my.learn.springbootdemo.io;


import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

public class StreamTest {
    @Test
    public void inputStreamTest() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/test2.txt");
        int read;
        while (  ( read = fileInputStream.read() ) != -1) {

//            System.out.print(read);
            System.out.print((char) read);
        }
        fileInputStream.close();


    }

    @Test
    public void buffInputStreamTest() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/test2.txt");
        byte[] buffer = new byte[15];
        int len = fileInputStream.read(buffer);
        System.out.println(len);
        System.out.println(Arrays.toString(buffer));
//        当数据长度小于数组长度，将末尾无效数据截断
        String string = new String(Arrays.copyOfRange(buffer,0,len) );
        System.out.println(string);

    }

    @Test
    public void outputStreamTest() throws IOException {
        FileOutputStream outputStream = new FileOutputStream("src/test/test2.txt",true);

        byte[] arr = {99,98};
        outputStream.write(arr);
        outputStream.write(97);
        outputStream.close();
    }

}
