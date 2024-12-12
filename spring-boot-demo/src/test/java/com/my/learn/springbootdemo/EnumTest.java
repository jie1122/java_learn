package com.my.learn.springbootdemo;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

public class EnumTest {
    @Test
    public  void enumTest()
    {
        Week sat = Week.SAT;
        System.out.println(sat.name() + "---" + sat.ordinal());

        Week sunday = Enum.valueOf(Week.class, "SUN");
        System.out.println(sunday.name() + "---" + sunday.ordinal());

        Week[] arr = Week.values();
        for (Week day : arr) {
            System.out.println(day.name() +"---"+ day.getIndex() +"---"+day.getChinese() );
        }

    }

}

/**
 * @AllArgsConstructor属于lombok包的注解
 * 自动生成含有所有属性的构造器
 *
 * 所有的枚举类型都是 Enum 类的子类，且无法被继承
 * 每个枚举常量都是所属枚举类的对象, 在类加载时会为每个常量创建实例对象。
 * 可以为枚举类型添加构造器，构造器只是在构造枚举常量的时候被调用。
 */
@AllArgsConstructor
enum Week
{

   MON, TUE, WED, THU, FRI,
    SAT(6,"星期六") ,SUN(7,"星期日");

    // 无参构造
    private Week()
    {
        System.out.println("Constructor called for : " + this.toString() +"---" +this.ordinal());
    }

    private int index;
    private String chinese;


    public int getIndex() {
        return index;
    }

    public String getChinese() {
        return chinese;
    }
}
