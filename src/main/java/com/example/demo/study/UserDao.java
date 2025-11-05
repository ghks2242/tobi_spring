package com.example.demo.study;

import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.*;

public /*abstract*/ class UserDao {

    // 3번째 상속이라는 제약에서 벗어나 다른방식으로 확장
    private ConnectionMaker connectionMaker;

    // p127 생성자 제거 메소드 방식의 DI 로 변경
    // 기존생성자로 DI 하는건 주석처리안했음 일부러
    public UserDao(ConnectionMaker c) {
        this.connectionMaker = c;
    }

    public UserDao() {

    }

    public void setConnectionMaker(ConnectionMaker c) {
        this.connectionMaker = c;
    }
    //

    // 2번째 추상메서드 를 할용하여 확장
//    public abstract Connection getConnection() throws Exception;

    // 1번째 add 와 get 의 중복 메서드 추출
//    private Connection getConnection() throws ClassNotFoundException, SQLException {
//        Class.forName("org.h2.Driver");
//        Connection c = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/tobi", "sa", "");
//        return c;
//    }

    public void add(User user) throws Exception {
//        Connection c = getConnection();
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values (?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }


    public User get(String id) throws Exception {
//        Connection c = getConnection();
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        User user = null;
        if(rs.next()) {
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        };

        rs.close();
        ps.close();
        c.close();

        if(user == null) throw new EmptyResultDataAccessException(1);

        return user;
    }

    public void deleteAll() throws Exception {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement("delete from users");
        ps.executeUpdate();

        ps.close();
        c.close();

    }


    public int getCount() throws Exception {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement("select count(*) from users");
        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        ps.close();
        c.close();

        return count;
    }

}
