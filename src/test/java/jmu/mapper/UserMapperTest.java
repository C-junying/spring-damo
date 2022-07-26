package jmu.mapper;

import jmu.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class UserMapperTest {
    @Autowired
    UserMapper userMapper;
    @Test
    void selectAll() {
        System.out.println(userMapper.selectAll());
    }
    @Test
    void userSearch() {

        System.out.println(userMapper.userSearch(new User("","张",null,null,null,null,null,null)));
    }

    @Test
    void deleteUser() {
        if(userMapper.deleteUser("123456789123456789")>0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
    }

    @Test
    void insertUser() throws ParseException {
        User user = new User("123456789123456789","列宽","123456",0,"123456","123@qq.com"
                ,new SimpleDateFormat("yyyy-MM-dd").parse("2000-12-25"),0);
        if(userMapper.insertUser(user)>0){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }
    }

    @Test
    void selectID() {
        System.out.println(userMapper.selectID("123456789123456789"));
    }
    @Test
    void selectUser() {
        System.out.println(userMapper.selectUser("520221200112252001","123456"));
    }
    @Test
    void updateUser() throws ParseException {
        User user = new User("123456789123456789","列宽as","12345126",0,"12213456","123@qq.com"
                ,new SimpleDateFormat("yyyy-MM-dd").parse("2000-12-25"),10);
        if(userMapper.updateUser(user)>0){
            System.out.println("更新成功");
        }else{
            System.out.println("更新失败");
        }
    }
}