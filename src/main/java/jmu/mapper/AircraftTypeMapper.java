package jmu.mapper;

import jmu.pojo.AircraftType;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface AircraftTypeMapper {
    int deleteByID(int typeID);             //根据ID删除机型

    int insertAircraftType(AircraftType aircraftType);  //插入机型

    AircraftType selectByID(int typeID);                //根据ID查询机型

    int updateAircraftType(AircraftType aircraftType);  //修改机型

    @MapKey("type_id")
    Map<String, AircraftType> selectAll();         //返回所有机型
}
