package jmu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirTicket {
    private String airTicketID;        //用户机票(身份证)
    private String passengerID;         //乘机人ID
    private String orderID;            //订单ID
    private String ticketTypeID;       //机票类型ID
    private Integer totalCosts;         //总费用
    private Integer seatNumber;          //座位号
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bookTime;              //订票时间
    private Integer cabinID;            //舱位ID

    private Passenger passenger;        //乘客
    private Cabin cabin;                //舱位
}