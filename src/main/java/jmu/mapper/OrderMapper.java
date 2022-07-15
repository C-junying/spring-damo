package jmu.mapper;

import jmu.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {
    int deleteByID(String orderID);        //删除订单

    int insertOrder(Order order);          //插入订单

    Order selectByID(String orderID);      //查询订单

    List<Order> selectByUserID(String userID);      //查询用户所有订单

    int updateOrder(Order order);          //更新订单
}