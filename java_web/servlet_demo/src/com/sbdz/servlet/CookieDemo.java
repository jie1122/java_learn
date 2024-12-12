package com.sbdz.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/demo4")
public class CookieDemo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        设置 响应头 文件类型 字符集 cookie
        resp.setContentType("text/html;charset=utf-8");
        Cookie cookie = new Cookie("age","22");
        resp.addCookie(cookie);
//        写入字符数据
        PrintWriter pw = resp.getWriter();
        pw.write("<h1>cookie test</h1>");
        pw.write("<h2>key:age,value:22</h2>");
        pw.write("<a href='/demo5'>image</a>");


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
