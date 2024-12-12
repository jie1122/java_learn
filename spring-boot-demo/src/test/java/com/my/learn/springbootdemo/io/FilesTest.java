package com.my.learn.springbootdemo.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesTest {
    @Test
    public void  readFileTest() throws IOException {
        Path path1 = Paths.get("src", "test" );
        Path javaPath = path1.resolve("java/com/my/learn/springbootdemo/io/FileTest.java");

        List<String> strings = Files.readAllLines(javaPath);
        strings.forEach( e ->{
            System.out.println(e);
        });
    }

    @Test
    public void wirteFileTest() throws IOException {
        Path path = Paths.get("src", "test", "test.txt");
        Path path1 = Paths.get("src", "test", "test1.txt");
        Files.write(path, "abcd".getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void copyFileTest() throws IOException {
        Path path = Paths.get("src", "test", "test.txt");
        Path path1 = Paths.get("src", "test", "test1.txt");
        Files.copy(path, path1);
    }
    @Test
    public  void getFiles() throws IOException {
        Path javaPath = Paths.get("src", "test","java/com/my/learn/springbootdemo/io");
        Stream<Path> fileList = Files.list(javaPath);
        List<Path> collect = fileList.collect(Collectors.toList());
        System.out.println( collect.toArray().toString() );
        System.out.println( Arrays.toString(collect.toArray()));
    }
}
