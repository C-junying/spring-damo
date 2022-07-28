package jmu.service.impl;

import jmu.mapper.OnFlightMapper;
import jmu.mapper.PassengerMapper;
import jmu.mapper.UserMapper;
import jmu.pojo.OnFlight;
import jmu.pojo.Passenger;
import jmu.pojo.User;
import jmu.service.UserService;
import jmu.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PassengerMapper passengerMapper;
    @Autowired
    private OnFlightMapper onFlightMapper;
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
        user.setBalance(0);
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


    @Transactional
    public void updateUser(User user) {
        User result = userMapper.selectID(user.getUserID());
        if(result == null){
            throw new UserNotFoundException("用户数据不存在");
        }
        Integer rows = userMapper.updateUser(user);
        if(rows!=1){
            throw new UserUpdateException("更新用户数据产生未知异常");
        }
//        return userMapper.updateUser(user) > 0;
    }
    @Override
    public List<Passenger> userPassenger(String userID) {
        User result = userMapper.selectID(userID);
        if(result == null){
            throw new UserNotFoundException("用户数据不存在");
        }
        List<Passenger> list = passengerMapper.selectUserByID(userID);
        return list;
    }

    @Override
    public void passengerAdd(Passenger passenger) {
        User userResult = userMapper.selectID(passenger.getUserID());
        if(userResult == null){
            throw new UserNotFoundException("用户数据不存在");
        }
        Passenger passengerResult = passengerMapper.selectByID(passenger.getUserID(),passenger.getPassengerID());
        if(passengerResult !=null){
            throw new PassengerDuplicateException("用户乘机人重复异常");
        }
        Integer rows = passengerMapper.insertPassenger(passenger);
        if(rows!=1){
            throw new InsertException("添加用户乘机人数据出现未知错误，请联系系统管理员");
        }
    }

    @Override
    public void passengerDelete(String userID, String passengerID) {
        Passenger passenger = passengerMapper.selectByID(userID,passengerID);
        if(passenger == null){
            throw new PassengerNotFoundException("乘机人不存在异常");
        }
        Integer rows = passengerMapper.deleteByID(userID,passengerID);
        if(rows!=1){
            throw new PassengerDeleteException("删除乘机人时产生未知异常");
        }
    }

    @Override
    public void userPay(String userID, Integer balance) {
        User result = userMapper.selectID(userID);
        if(result == null){
            throw new UserNotFoundException("用户数据不存在");
        }
        Integer rows = userMapper.updateUserBalance(userID,balance+result.getBalance());
        if(rows != 1){
            throw new PayException("支付异常");
        }
    }

    @Override
    public List<OnFlight> queryTakeoffAndArrive(String startCityName, String endCityName, String takeoff, String arrive) {
        takeoff = takeoff+" 00:00:00";
        arrive = arrive+" 24:00:00";
        List<OnFlight> list =  onFlightMapper.queryTakeoffAndArrive(startCityName,endCityName,takeoff,arrive);
        return list;
    }
}
