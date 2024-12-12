package com.sbdz.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
@WebServlet("/demo5")
public class ContextDemo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //      获取servletContext 对象和文件真实路径
        ServletContext servletContext = getServletContext();
        String realpath = servletContext.getRealPath("/img/2.jpg");
        System.out.println(realpath);

//        输入字节流
        FileInputStream fis = new FileInputStream(realpath);
//        输出字节流
        OutputStream ops = resp.getOutputStream();
        byte[] buffArray = new byte[8*1024];
        int len ;
        while ((len=fis.read(buffArray))!=-1){
            ops.write(buffArray,0,len);
        }
        fis.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
