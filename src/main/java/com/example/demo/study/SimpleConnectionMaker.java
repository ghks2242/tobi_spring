package com.example.demo.study;

import java.sql.Connection;
import java.sql.DriverManager;

public class SimpleConnectionMaker {

    public Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/tobi", "sa", "");
        return c;
    }
}
