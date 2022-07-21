package jmu.service;

import jmu.pojo.User;

import java.util.List;

public interface UserService {
    List<User> selectAll();     //返回所有用户

    boolean deleteUser(String userID);      //删除用户

    void insertUser(User user);          //插入用户

    User login(User user);          //登录操作
    User selectID(String userID);     //根据ID返回用户

    boolean selectUser(User user);     //判断用户是否存在

    boolean updateUser(User user);          //更新用户
}
