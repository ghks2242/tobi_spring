package com.example.demo.study;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserTest {

    public static void main(String[] args) throws Exception {

//        UserDao dao = new DaoFactory().userDao();

//        스프링 IOC 를 이용하는걸로 변환
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        // getBean 의 파라미터인 'userDao' 는 DaoFactory 에 @Bean 이라 붙은 어노테이션을 userDao 라는 이름의 메소드에 붙였는데 이메소드 이름이 빈의 이름이된다.
        // getBean 은 기본적으로 Object 타입을 리턴하게되어있다. 그래서 매번 다시 캐스팅해줘야하는 부담이있다 자바 5이상의 제네릭 메소드 방식을 이용해 2번쨰 파라미터에 리턴타입을주면 깔끔하게 캐스팅할수있다.
        UserDao dao = context.getBean("userDao", UserDao.class);
//

        User user = new User();
        user.setId("whiteship");
        user.setName("Baek");
        user.setPassword("married");

        dao.add(user);
        System.out.println(user.getId() + " registered successfully");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());
        System.out.println(user2.getId() + " fetched successfully");
    }
}

