package com.sbdz;

public class Demo {
    public static void main (String[] args) {
        System.out.println("demo2");
        Show.show();
        System.out.println(Show.num);
        Show show = new Show();
        show.show();
        System.out.println(show.num);
    }
}
class Show{
    int demo = 20;
    long test = demo;
    static int num = 10;
    static void show(){
        System.out.println(num);
    }
}