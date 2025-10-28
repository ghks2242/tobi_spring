package study;

import com.example.demo.study.DaoFactory;
import com.example.demo.study.UserDao;
import org.assertj.core.api.Assertions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


// 싱글톤 확인
public class DaoFactoryTest {

    public static void main(String[] args) {
        makeDaoFactory(); // 직접 DaoFactory 생성해서 할경우
        System.out.println();
        springDaoFactory(); // 스프링 ioc 를 이용한경우

    }

    public static void makeDaoFactory() {
        DaoFactory factory = new DaoFactory();
        UserDao dao1 = factory.userDao();
        UserDao dao2 = factory.userDao();

        System.out.println(dao1);
        System.out.println(dao2);
    }

    public static void springDaoFactory() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao1 = context.getBean("userDao", UserDao.class);
        UserDao dao2 = context.getBean("userDao", UserDao.class);

        System.out.println(dao1);
        System.out.println(dao2);
    }
}
