package com.example.demo.study.convertXml;

import com.example.demo.study.User;
import com.example.demo.study.UserDao;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserDaoTestXml {

    public static void main(String[] args) throws Exception {
//        xml로 설정한 bean 을 불러오기위해서는 아래 context 를 사용한다
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("convertXml/DaoFactoryXml.xml");
        UserDao dao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("whiteship");
        user.setName("Baek XML");
        user.setPassword("married");

        dao.add(user);
        System.out.println(user.getId() + " registered successfully");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());
        System.out.println(user2.getId() + " fetched successfully");

        if (!user.getName().equals(user2.getName())) {
            System.out.println("테스트 실패 (name)");
        } else if (!user.getPassword().equals(user2.getPassword())) {
            System.out.println("테스트 실패 (password)");
        } else {
            System.out.println("조회 테스트 성공.");
        }
    }
}
