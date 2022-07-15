package jmu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cabin {
    private Integer cabinID;        //舱位ID
    private String cabinClass;      //舱位等级
}