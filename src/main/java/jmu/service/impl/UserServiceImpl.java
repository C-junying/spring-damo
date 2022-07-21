package jmu.service.impl;

import jmu.mapper.UserMapper;
import jmu.pojo.User;
import jmu.service.UserService;
import jmu.service.ex.InsertException;
import jmu.service.ex.PasswordNotMatchException;
import jmu.service.ex.UserNotFoundException;
import jmu.service.ex.UserIDDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Transactional
    public boolean deleteUser(String userID) {
        return userMapper.deleteUser(userID) > 0;
    }

    @Transactional
    public void insertUser(User user) {
        // 根据参数user对象获取注册的用户名
        String userID = user.getUserID();
        // 调用持久层的User selectID(String userID)方法，根据用户名查询用户数据
        User result = userMapper.selectID(userID);
        // 判断查询结果是否不为null
        if (result != null) {
        // 是：表示用户名已被占用，则抛出UsernameDuplicateException异常
            throw new UserIDDuplicateException("尝试注册的ID[" + userID +"]已经被占用");
        }
        //设置用户权限
        user.setPermission(0);
        // 调用持久层Integer insert(User user)方法，执行注册并获取返回值(受影响的行数)
        Integer rows = userMapper.insertUser(user);
        // 判断受影响的行数是否不为1
        if (rows != 1) {
        // 是：插入数据时出现某种错误，则抛出InsertException异常
            throw new InsertException("添加用户数据出现未知错误，请联系系统管理员");
        }
    }

    @Override
    public User login(User user) {
        //检查用户是否存在
        User result = userMapper.selectID(user.getUserID());
        if( result == null){
            throw new UserNotFoundException("用户数据不存在");
        }
        //检查用户密码是否匹配
        if(!result.getUserPassword().equals(user.getUserPassword())){
            throw new PasswordNotMatchException("用户密码错误");
        }
        return result;
    }

    @Override
    public User selectID(String userID) {
        return userMapper.selectID(userID);
    }

    @Override
    public boolean selectUser(User user) {
        if(userMapper.selectUser(user.getUserID(),user.getUserPassword())!=null){
            return true;
        }
        return false;
    }

    @Transactional
    public boolean updateUser(User user) {
        return userMapper.updateUser(user) > 0;
    }
}
