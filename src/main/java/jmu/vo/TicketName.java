package jmu.vo;

import jmu.pojo.AirTicket;
import jmu.pojo.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketName {
    private String ticketTypeID;           //航班机票
    private String startCityName;              //起始城市
    private String startAirportName;          //起始站机场
    private String startStationName;          //起始站
    private String endCityName;                 //终点城市
    private String endAirportName;            //终点站机场
    private String endStationName;            //终点站
    private Integer ticketPricing;          //机票定价
    private Integer discount;               //折扣
    private Integer insuranceCosts;         //保险费用
    private Integer ticketCount;            //总数
    private Integer remainingTickets;       //余量

    private List<Boolean> listNumber;             //使用的座位号
    private List<AirTicket> airTickets;     //航班所有机票
}
