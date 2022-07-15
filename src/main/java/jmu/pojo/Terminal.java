package jmu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Terminal {
    private String terminalID;         //航站楼ID
    private String airportID;           //机场ID
    private String terminalName;        //航站楼名
}