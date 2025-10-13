package com.example.demo.study;

import java.sql.*;

public  class UserDao {

    // 3번째 상속이라는 제약에서 벗어나 다른방식으로 확장
    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker c) {
        this.connectionMaker = new NConnectionMaker();
    }

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
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

}
