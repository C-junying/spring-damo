package jmu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AircraftType {
    private String typeID;         //机型ID
    private String typeName;        //机型
    private Integer capacity;       //最大容客量
}
