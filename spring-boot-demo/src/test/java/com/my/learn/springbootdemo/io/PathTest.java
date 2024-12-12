package com.my.learn.springbootdemo.io;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {
    @Test
    public void test() {
        Path path = Paths.get("src", "test", "test.txt");
        Path path1 = Paths.get("src", "test" );
        Path javaPath = path1.resolve("java/com/my/learn/springbootdemo/io/FileTest.java");
        System.out.println(path);
        System.out.println(path1);
        System.out.println(javaPath);
        File file = javaPath.toFile();

    }
}
