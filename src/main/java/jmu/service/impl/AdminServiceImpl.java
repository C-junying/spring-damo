package jmu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jmu.mapper.*;
import jmu.pojo.*;
import jmu.service.AdminService;
import jmu.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserMapper userMapper;





    @Override
    public PageInfo<User> selectAllUser(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<User> list =  userMapper.selectAll();
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void userDelete(User user) {
        User result = userMapper.selectID(user.getUserID());
        if(result == null){
            throw new UserNotFoundException("用户不存在异常");
        }
        Integer rows = userMapper.deleteUser(user.getUserID());
        if(rows!=1){
            throw new UserDeleteException("删除用户时产生未知异常");
        }
    }

    @Override
    public PageInfo<User> usertSearch(User user, Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<User> list =  userMapper.userSearch(user);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

}
