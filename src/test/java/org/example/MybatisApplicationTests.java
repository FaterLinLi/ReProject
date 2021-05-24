package org.example;


import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisApplicationTests {

  @Autowired
  private UserMapper userMapper;

  void contextLoads(){
      List<User> users = userMapper.selectList(null);
      users.forEach(System.out::println);
  }

}
