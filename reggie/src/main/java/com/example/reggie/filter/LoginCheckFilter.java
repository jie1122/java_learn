package com.example.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.example.reggie.common.MyThreadContext;
import com.example.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    private static AntPathMatcher antPathMatcher = new AntPathMatcher();

    private static String[] urls = {
            "/employee/login",
            "/employee/logout",
            "/backend/**",
            "/front/**",
            "/common/**",
            "/user/sendMsg",
            "/user/login",
            "/doc.html",
            "/webjars/**",
            "/swagger-resources",
            "/v2/api-docs",
    };

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        log.info("拦截到请求："+ requestURI);

        //检查是否在白名单
        boolean check = check(requestURI);
        if (check) {
            log.info("白名单："+ requestURI);
            filterChain.doFilter(request,response);
            return ;
        }
        // 判断登录状态
        if (request.getSession().getAttribute("employee")!=null) {
            log.info("已登录："+ requestURI);
            Long empId = (Long)request.getSession().getAttribute("employee");
            MyThreadContext.setValue(empId);
            filterChain.doFilter(request,response);
            return ;
        }
        // 判断移动端登录状态
        if (request.getSession().getAttribute("user")!=null) {
            log.info("已登录："+ requestURI);
            Long userId = (Long)request.getSession().getAttribute("user");
            MyThreadContext.setValue(userId);
            filterChain.doFilter(request,response);
            return ;
        }
        //未登录
        log.info("未登录："+ requestURI);

//        未登录在前端处理
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));


    }

    public boolean check(String uri) {
        for (String url : urls) {
            boolean isMatch = antPathMatcher.match(url, uri);
            if (isMatch) {
                return true;
            }
        }
        return false;
    }
}
