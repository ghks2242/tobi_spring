package com.example.demo.study;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 기존의 수동으로 설정한 IOC 를 스프링 IOC 로 변경 @Configuration, @Bean 을 붙여서 스프링에게 위임해준다
// @Configuration (설정을 담당하는 클래스라고 인식할수있도록)
// @Bean (오브젝트를 만들어주는 메소드에)
@Configuration
public class DaoFactory {

//    @Bean
//    public UserDao userDao() {
//        UserDao userDao = new UserDao(connectionMaker());
//        return userDao;
//    }

//    public AcountDao acountDao() {
//        AcountDao acountDao = new AcountDao(connectionMaker());
//        return acountDao;
//    }
//
//    public MessageDao messageDao() {
//        MessageDao messageDao = new MessageDao(connectionMaker());
//        return messageDao;
//    }

//    @Bean
//    public ConnectionMaker connectionMaker() {
//        return new NConnectionMaker();
//    }

}
