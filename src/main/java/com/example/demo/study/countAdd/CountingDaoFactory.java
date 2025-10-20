package com.example.demo.study.countAdd;

import com.example.demo.study.ConnectionMaker;
import com.example.demo.study.NConnectionMaker;
import com.example.demo.study.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {

    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new CountingConnectionMaker(relalConnectionMaker());
    }

    @Bean
    public ConnectionMaker relalConnectionMaker() {
        return new NConnectionMaker();
    }

}
