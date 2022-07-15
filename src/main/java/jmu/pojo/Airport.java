package jmu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airport {
    private String airportID;           //机场三字码
    private String cityID;              //城市ID
    private String airportName;         //机场名

    private List<Terminal> terminals;   //航站楼
}