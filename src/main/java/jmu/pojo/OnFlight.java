package jmu.pojo;

import jmu.vo.TicketName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnFlight {
    private String onFlightID;          //执飞航班ID
    private String typeID;              //机型ID
    private String ticketTypeID;        //航班机票ID
    private String currentStatus;       //当前状态
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date estimatedTakeoffTime;      //预计起飞时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date actualTakeoffTime;         //实际起飞时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date estimatedArrivalTime;      //预计到达时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date actualArrivalTime;         //实际到达时间
    private int boardingGate;               //登机口

    private TicketName ticketType;          //航班机票
}