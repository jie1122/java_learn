package com.sbdz.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/demo3")
public class HttpServletDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        resp.getWriter().println("<h1>12345678</h1>");
//        resp.sendRedirect("http://www.baidu.com");
        System.out.println(req);
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //2.输出数据
        System.out.println(req);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();

        pw.write("<h1>hello response</h1>");
        pw.write("你好啊啊啊 response");
    }
}
