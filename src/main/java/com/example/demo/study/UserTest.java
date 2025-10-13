package com.example.demo.study;

public class UserTest {

  public static void main(String[] args) throws Exception {
    UserDao dao = new UserDao(new NConnectionMaker());

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

