package com.example.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.reggie.common.R;
import com.example.reggie.domain.User;
import com.example.reggie.service.UserService;
import com.example.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        String phone = user.getPhone();
        if (StringUtils.isNotEmpty(phone)) {
//            生成验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
//            发送验证码
//            sendCode()
            log.info("验证码："+ code);
//           保存验证码到session,和手机号绑定
//            session.setAttribute(phone,code);
//           保存验证码到redis,和手机号绑定
            ValueOperations opsForValue = redisTemplate.opsForValue();
            opsForValue.set(phone,code,5, TimeUnit.MINUTES);

            return R.success("发送成功");

        }
        return R.error("发送失败");
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session) {
//        获取用户提交的手机号和code
        String phone = (String) map.get("phone");
        String code = (String) map.get("code");
//        通过手机号获取session 中的code
//        String codeInSession = (String) session.getAttribute(phone);
        ValueOperations opsForValue = redisTemplate.opsForValue();
        String codeInSession = (String) opsForValue.get(phone);
//        比对
        if (StringUtils.isNotEmpty(codeInSession) && code.equals(codeInSession) ) {
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);
            User user = userService.getOne(queryWrapper);
            if (user == null) {
                User newUser = new User();
                newUser.setPhone(phone);
                newUser.setStatus(1);
                userService.save(newUser);
            }
            user = userService.getOne(queryWrapper);
            session.setAttribute("user",user.getId());
            redisTemplate.delete(phone);
            return R.success(user);
        }
        return R.error("登录失败");
    }
}
