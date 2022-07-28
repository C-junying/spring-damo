package jmu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String orderID;        //订单ID
    private String userID;          //用户ID
    private Integer orderCosts;     //总费用
    private String paymentStatus;   //支付状态

    List<AirTicket> airTickets;
}