package jmu.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userID;      //用户ID
    private String userName;    //用户姓名
    private String userPassword;    //用户密码
    private Integer permission;     //用户权限
    //可要可不要
    private String phone;         //电话号码
    private String email;               //邮箱
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthDate;             //出生日期
    private Integer balance;            //余额

    public User(String userID,String userPassword){
        this.userID = userID;
        this.userPassword = userPassword;
    }
}
