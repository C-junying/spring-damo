package jmu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Luggage {
    private Integer luggageID;      //行李ID
    private String airTicketID;     //机票ID
    private Double weight;          //重量
    private Double length;          //长
    private Double width;           //宽
    private Double height;          //高
    private Double shippingCosts;   //托运费
}