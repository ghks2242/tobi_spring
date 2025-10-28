package com.example.demo.study.convertXml;

import com.example.demo.study.User;
import com.example.demo.study.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class UserDaoTestXmlTest {

    @Test
    void addAndGet() throws Exception {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("convertXml/DaoFactoryXml.xml");
        UserDao dao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("gyumee");
        user.setName("박상철");
        user.setPassword("springno1");

        dao.add(user);

        User user2 = dao.get(user.getId());
                    // 디비에서꺼낸값, 실제로내가기대한값
        assertThat(user2.getName(), is(user.getName()));
        assertThat(user2.getPassword(), is(user.getPassword()));
    }
}
