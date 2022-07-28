package jmu.service;

import jmu.pojo.OnFlight;
import jmu.pojo.Passenger;
import jmu.pojo.User;

import java.text.ParseException;
import java.util.List;

public interface UserService {
    List<User> selectAll();     //返回所有用户

    boolean deleteUser(String userID);      //删除用户

    void insertUser(User user);          //插入用户

    User login(User user);          //登录操作
    User selectID(String userID);     //根据ID返回用户

    void updateUser(User user);          //更新用户
    List<Passenger> userPassenger(String userID);   //返回用户的乘机人

    void passengerAdd(Passenger passenger); //添加乘机人
    void passengerDelete(String userID,String passengerID); //删除乘机人

    void userPay(String userID,Integer balance);    //充值

    /**
     * 根据起始终止地以及时间查询
     * @param startCityName 起始城市
     * @param endCityName   到达城市
     * @param takeoff   最早时间
     * @param arrive    最晚时间
     * @return
     */
    List<OnFlight> queryTakeoffAndArrive(String startCityName,String endCityName,String takeoff,String arrive);

    void orderCreate(String flight_infor,String passList,String userID) throws ParseException;    //订单生成
}
