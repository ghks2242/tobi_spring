//package com.example.demo.study;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//public class NUserDao extends UserDao {
//
 // UserDao 추상메서드를 주석처리 하여 소스 주석처리
//    @Override
//    public Connection getConnection() throws Exception {
//        Class.forName("org.h2.Driver");
//        Connection c = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/tobi", "sa", "");
//        return c;
//    }
//}
