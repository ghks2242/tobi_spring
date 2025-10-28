package com.example.demo.study.datasource.convertXml;

import com.example.demo.study.User;
import com.example.demo.study.datasource.UserDaoData;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserTestDataXml {

    public static void main(String[] args) throws Exception {


        GenericXmlApplicationContext context = new GenericXmlApplicationContext("datasource/convertXml/dataSource.xml");
        UserDaoData dao = context.getBean("userDao", UserDaoData.class);
//

        User user = new User();
        user.setId("whiteship");
        user.setName("Baek Data XML");
        user.setPassword("married");

        dao.add(user);
        System.out.println(user.getId() + " registered successfully");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());
        System.out.println(user2.getId() + " fetched successfully");
    }
}

