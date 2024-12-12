package com.sbdz.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcDemo2 {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/learn?serverTimezone=UTC";

        Connection con = DriverManager.getConnection(url,"root","root");
    }
}
