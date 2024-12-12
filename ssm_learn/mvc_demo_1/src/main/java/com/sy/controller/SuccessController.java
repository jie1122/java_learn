package com.sy.controller;

import com.sy.dao.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/success")
public class SuccessController {
    @RequestMapping("/successful")
    public String success(){
//        直接返回视图
        System.out.println("success.....");
        return "/view/success.jsp";
    }

    @RequestMapping("/mav")
    public ModelAndView  modelView(ModelAndView modelAndView){
//       返回 modelAndView
        modelAndView.addObject("mav","modelAndView");
        modelAndView.setViewName("/view/success.jsp");
        return modelAndView;
    }

    @RequestMapping("/m")
    public String model(Model model){
        // Model 注入视图
        model.addAttribute("model","it is a model ");
        return "/view/success.jsp";
    }

    @RequestMapping("string1")
    @ResponseBody
    //直接返回字符串 需要 @ResponseBody
    public String resString(){
        return "string res";
    }


    @RequestMapping("json")
    @ResponseBody
    public Person resJson() throws Exception {
        // 配置jackson 返回对象自动转换为json
        Person person = new Person();
        person.setAge("20");
        person.setName("zhangSan");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(person);

        return person;
    }
}
