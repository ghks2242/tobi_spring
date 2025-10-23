package com.example.demo.study.datasource;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class DaoFactoryData {

    @Bean
    public UserDaoData userDao() {
        UserDaoData userDao = new UserDaoData();
        userDao.setDataSource(dataSource());
        return userDao;
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUrl("jdbc:h2:tcp://localhost/~/tobi");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

}
