package com.example.demo.study.convertXml;

import com.example.demo.study.User;
import com.example.demo.study.UserDao;
import com.example.demo.study.datasource.UserDaoData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Test 시에는 UserDaoTestXmlTest 객체가 매번새롭게 생성되어서 테스트에 독립성을 보장함
/*
* 1️⃣ UserTest의 인스턴스 1개 생성
* 2️⃣ @BeforeEach 실행
* 3️⃣ testA() 실행
* 4️⃣ 객체 폐기
*
* 5️⃣ UserTest의 새 인스턴스 다시 생성
* 6️⃣ @BeforeEach 실행
* 7️⃣ testB() 실행
* 8️⃣ 객체 폐기
* */

//@SpringBootTest
/*
* @SpringBootTest 안에는 이미
* @ExtendWith(SpringExtension.class) + @ContextConfiguration 기능이 포함되어 있습니다.
* */
@ContextConfiguration(locations = "classpath:datasource/convertXml/test-dataSource.xml") // 설정 파일 지정
@ExtendWith(SpringExtension.class) // 스프링 테스트 실행기 등록
//@DirtiesContext // 테스트메소드에서 애플리케이션 컨택스트의 구성이나 상태를 변경한다는것을 테스트 컨택스트 프레임워크에 알려준다.
public class UserDaoTestXmlTest {

    @Autowired
    private ApplicationContext context; // 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 값이 주입된다

    /*
     * class 변수 -> 인스턴스변수
     * class 변수에 static -> 클래스변수
     * 메소드안의 변수 -> 지역변수
     * */
    @Autowired
    private UserDaoData dao;    // setUp 메소드에서 만드는 객체를 테스트 메소드에서 사용할수있게 인스턴스변수로 추가

    private User user1;
    private User user2;
    private User user3;


    @BeforeEach
    public void setUp() {
//      첫번째로 공통된 항목을 테스트 전에 세팅하도록 뺀다
//        GenericXmlApplicationContext context = new GenericXmlApplicationContext("convertXml/DaoFactoryXml.xml");
//        this.dao = context.getBean("userDao", UserDao.class);
//       세번쨰로 위의 소스는 매 테스트마다 애플리케이션콘택스트가 만들어진다 이걸 어노테이션으로 정리해보겟다.

        // 테스트에서 사용할 dataSource 오브젝트를 직접생성
//        DataSource dataSource = new SingleConnectionDataSource("jdbc:h2:tcp://localhost/~/tobi","sa","",true);
//        dao.setDataSource(dataSource);

//      두번째로 user 데이터 생성하는부분도 추출하였다.
        this.user1 = new User("gyumee", "박상철", "springno1");
        this.user2 = new User("leegw700", "이길원", "springno2");
        this.user3 = new User("bumjin", "박범진", "springno3");

        System.out.println(this.context);
        System.out.println(this);

    }


    @Test
    void addAndGet() throws Exception {
//        GenericXmlApplicationContext context = new GenericXmlApplicationContext("convertXml/DaoFactoryXml.xml");
//        UserDao dao = context.getBean("userDao", UserDao.class);

//        User user1 = new User("gyumee", "박상철", "springno1");
//        user.setId("gyumee");
//        user.setName("박상철");
//        user.setPassword("springno1");

//        User user2 = new User("leegw700", "이길원", "springno2");

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User userget1 = dao.get(user1.getId());
        // 디비에서꺼낸값, 실제로내가기대한값
        assertThat(userget1.getName(), is(user1.getName()));
        assertThat(userget1.getPassword(), is(user1.getPassword()));

        User userget2 = dao.get(user2.getId());
        assertThat(userget2.getName(), is(user2.getName()));
        assertThat(userget2.getPassword(), is(user2.getPassword()));
    }


    @Test
    public void count() throws Exception {
//        GenericXmlApplicationContext context = new GenericXmlApplicationContext("convertXml/DaoFactoryXml.xml");
//        UserDao dao = context.getBean("userDao", UserDao.class);

//        User user1 = new User("gyumee", "박상철", "springno1");
//        User user2 = new User("leegw700", "이길원", "springno2");
//        User user3 = new User("bumjin", "박범진", "springno3");

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));
    }

    //    @Test(expected=EmptyResultDataAccessException.class) // junit4 문법이므로 5 문법으로 바꿔서 테스트 하겠다.
    @Test
    public void getUserFailure() throws Exception {
//        GenericXmlApplicationContext context = new GenericXmlApplicationContext("convertXml/DaoFactoryXml.xml");
//        UserDao dao = context.getBean("userDao", UserDao.class);

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        assertThrows(EmptyResultDataAccessException.class, () -> {
            dao.get("unkowon_id");
        });
    }

}
