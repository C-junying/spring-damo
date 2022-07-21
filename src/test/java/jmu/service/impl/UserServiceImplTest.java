package jmu.service.impl;

import jmu.pojo.User;
import jmu.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Test
    void login() {
        User user = userService.login(new User("123456","chaiy","1234567",null,null,null,null,null));
        System.out.println(user);
    }
    @Test
    void insertUser(){
        userService.insertUser(new User("123456","chaiy","1234567"
                ,null,null,null,null,null));
    }
}