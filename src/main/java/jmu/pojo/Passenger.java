package jmu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
    private String userID;              //用户ID
    private String passengerID;         //身份证
    private String passengerName;       //姓名
    private String sex;                //性别
    private String phone;               //电话号码
}