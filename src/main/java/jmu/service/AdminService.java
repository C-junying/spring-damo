package jmu.service;

import com.github.pagehelper.PageInfo;
import jmu.pojo.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface AdminService {

    PageInfo<User> selectAllUser(Integer currentPage, Integer pageSize);   //返回所有用户
    void userDelete(User user);  //删除用户
    PageInfo<User> usertSearch(User user,Integer currentPage, Integer pageSize);  //查找用户



}
