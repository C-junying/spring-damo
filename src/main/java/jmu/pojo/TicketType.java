package jmu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketType {
    private String ticketTypeID;           //航班机票
    private String startAirportID;          //起始站机场ID
    private String startStationID;          //起始站ID
    private String endAirportID;            //终点站机场ID
    private String endStationID;            //终点站ID
    private Integer ticketPricing;          //机票定价
    private Integer discount;               //折扣
    private Integer insuranceCosts;         //保险费用
    private Integer ticketCount;            //总数
    private Integer remainingTickets;       //余量

}