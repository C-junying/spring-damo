package jmu.service;

import com.github.pagehelper.PageInfo;
import jmu.pojo.*;

import java.text.ParseException;
import java.util.List;

public interface AdminFlightService {
    PageInfo<OnFlight> selectAllOnFlights(Integer currentPage, Integer pageSize);   //返回所有航班
    TicketType selectByTicketID(String ticketType); //返回航班信息
    List<Passenger> selectFlightAllPassenger(String ticketTypeID); //返回航班乘客
    void onFlightDelete(String onFlightID,String estimatedTakeoffTime) throws ParseException;  //删除航班
    void addTerminal(Terminal terminal);    //增加航站楼
    //增加航班
    void addFlight(String onFlightID,String typeID,String estimatedTakeoffTime,String estimatedArrivalTime,String boardingGate,TicketType ticketType) throws ParseException;

    PageInfo<OnFlight> onFlightSearch(OnFlight onFlight,Integer currentPage, Integer pageSize);  //查找航班
    List<AircraftType> queryAllAircraftTypes();   //返回所有机型
}
