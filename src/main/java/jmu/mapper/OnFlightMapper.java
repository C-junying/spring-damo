package jmu.mapper;

import jmu.pojo.OnFlight;
import jmu.pojo.Passenger;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface OnFlightMapper {
    int deleteByIDTime(String onFlightID, Date takeoff);    //通过id时间删除航班

    int insertOnFlight(OnFlight onFlight);                  //插入航班

    List<OnFlight> selectByID(String onFlightID);           //通过ID返回航班

    OnFlight selectByIDTime(String onFlightID, Date takeoff);       //通过ID时间返回航班

    List<OnFlight> selectAllOnFlights();                            //返回所有航班

    int updateOnFlight(OnFlight onFlight,String onFlightID, Date takeoff);  //更新航班

    //A->B航班航线信息。当参数为空，则查询所有航班航线
    List<OnFlight> queryOnfligthAndTicket(String startCityName,String endCityName);     //A->B的航班
    List<OnFlight> queryTakeoffAndArrive(String startCityName,String endCityName,String takeoff,String arrive);//某个时间段的航班

    List<OnFlight> onFlightSearch(OnFlight onFlight);   //航班模糊查询

    List<Passenger> queryAllPassengers(String ticketTypeID);    //返回航班乘客
}
