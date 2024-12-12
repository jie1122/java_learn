package com.my.learn.springbootdemo.io;

import org.junit.jupiter.api.Test;

import java.io.*;

public class CharStreamTest {
    @Test
    public void writeFile(){
        File file = new File("src/test/test2.txt");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("123456");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public  void readFile() throws IOException {
        File file = new File("src/test/test2.txt");
        FileReader fileReader = new FileReader(file);

        int read;
        while ((read = fileReader.read()) != -1) {
            System.out.print((char) read);
        }


    }
    @Test
    public  void bufferReadFile() throws IOException {
        File file = new File("src/test/test2.txt");
        FileReader fileReader = new FileReader(file);

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null)
        {
            System.out.println(line);
        }

    }
}
