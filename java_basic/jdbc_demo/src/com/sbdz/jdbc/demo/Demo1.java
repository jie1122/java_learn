package com.sbdz.jdbc.demo;

import java.sql.*;

public class Demo1 {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/test_db?serverTimezone=UTC";
        Connection con = DriverManager.getConnection(url,"root","root");
        Statement statement = con.createStatement();
//        statement.executeUpdate("insert into employee values (null,'二二',33,2)");
        statement.executeUpdate("delete from employee where id=13;");
        ResultSet ret = statement.executeQuery("select * from employee");
        while (ret.next()){
            int id = ret.getInt(1);
            String name = ret.getString(2);
            int age = ret.getInt(3);
            int dep_id = ret.getInt(4);
            System.out.println("id:"+id+", name:"+name+", age:"+age+", dep_id:"+dep_id);
        }
        ret.close();
        statement.close();
        con.close();
    }

}
