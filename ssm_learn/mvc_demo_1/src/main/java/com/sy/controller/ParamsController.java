package com.sy.controller;

import com.sy.dao.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/params")
public class ParamsController {
    @ResponseBody
    @RequestMapping("demo1")
    //接收简单参数
    public String demo1(String name,String age){
        return name+":"+age;
    }
    @ResponseBody
    @RequestMapping("demo2")
    // 接收pojo
    public Person demo2(Person personName) throws Exception{
        return personName;
    }
    @ResponseBody
    @RequestMapping("demo3")
    // 接收数组
    public String[] demo3(String[]  array) throws Exception{
        return array;
    }
    @ResponseBody
    @RequestMapping("demo4")
//    指定请求参数， 默认必须，自动将对应参数赋值给形参
    public String demo4(@RequestParam("name") String userName){
        return userName;
    }
    @ResponseBody
    @RequestMapping("demo5")
//   获取servlet对象
    public String demo5(HttpServletRequest request, HttpServletResponse response,
                         HttpSession session){

        String s =  request.toString()+response.toString() +session.toString();
        return s;
    }
}
