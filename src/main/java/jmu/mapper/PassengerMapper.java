package jmu.mapper;

import jmu.pojo.Passenger;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PassengerMapper {
    int deleteByID(String userID,String passengerID);         //删除乘机人

    int insertPassenger(Passenger passenger);   //插入乘机人

    Passenger selectByID(String userID,String passengerID);   //根据ID返回乘机人

    List<Passenger> selectUserByID(String userID);  //根据userID返回所有ID

    int updatePassenger(Passenger passenger);   //修改乘机人
}
