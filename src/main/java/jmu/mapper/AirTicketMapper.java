package jmu.mapper;

import jmu.pojo.AirTicket;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface AirTicketMapper {
    int deleteByIDTime(String airTicketID, Date bookTime);          //删除机票

    int insertAirTicket(AirTicket airTicket);                   //插入机票

    AirTicket selectByIDTime(String airTicketID , Date bookTime);   //返回机票

    List<AirTicket> selectAirTicketsByOrderID(String orderID);  //返回所有机票

    int updateAirTicket(AirTicket airTicket);        //更新机票

    List<AirTicket> queryAirTicketPassenger(String airTicketID);      //返回机票对应的所有乘机人
}

