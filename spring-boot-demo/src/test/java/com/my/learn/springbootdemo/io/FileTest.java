package com.my.learn.springbootdemo.io;

import org.junit.jupiter.api.Test;

import java.io.*;

import java.util.Arrays;
import java.util.List;

public class FileTest {
    @Test
    public  void  queryFile(){
        File file = new File("D:\\home");
        System.out.println("exists --- "+file.exists());
        System.out.println("isDirectory --- "+file.isDirectory());
        String[] fileNames = file.list();
        List<String> strings = Arrays.asList(fileNames);
        System.out.println(strings);

        File[] files = file.listFiles();
        for (File file1 : files) {
            System.out.println(file1.getName());
        }
    }
    @Test
    public void createFile(){
//        File file = new File("D:\\home");
//        File file1 = new File("src\\test\\test.txt");
        File file1 = new File("src","test/test.txt");
        if (file1.exists()) {
            file1.delete();
        }
        try {
            file1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
