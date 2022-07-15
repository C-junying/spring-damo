package jmu.mapper;

import jmu.pojo.TicketType;
import jmu.vo.TicketName;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TicketTypeMapper {
    int deleteByID(String ticketTypeID);           //删除航班机票

    int insertTicketType(TicketType ticketType);    //插入航班机票

    TicketType selectByID(String ticketTypeID);     //根据ID查找航班机票

    @MapKey("ticket_type_id")
    Map<String,TicketType> selectAll();             //返回所有航班机票

    int updateTicketType(TicketType ticketType);    //更新航班机票

    TicketName queryTicketPassenger(String ticketTypeID);       //航线的乘客
}