package com.example.demo.study.countAdd;

import com.example.demo.study.ConnectionMaker;

import java.sql.Connection;
import java.sql.SQLException;

// 만약에 디비접속횟수가 몇번인지 알고싶어서 추가해야한다면
public class CountingConnectionMaker implements ConnectionMaker {

    int counter = 0;
    private ConnectionMaker realConnectinMaker;

    public CountingConnectionMaker(ConnectionMaker connectionMaker) {
       this.realConnectinMaker = connectionMaker;
    }

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        this.counter++;
        return realConnectinMaker.makeConnection();
    }

    public int getCounter() {
        return this.counter;
    }
}
