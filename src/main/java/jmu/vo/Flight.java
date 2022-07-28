package jmu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    private String onFlightID;          //执飞航班ID
    private String ticketTypeID;           //航班机票
    private String startStationName;          //起始站
    private String endStationName;            //终点站
    private String typeName;        //机型
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date estimatedTakeoffTime;      //预计起飞时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date estimatedArrivalTime;      //预计到达时间

}
