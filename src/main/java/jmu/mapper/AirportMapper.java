package jmu.mapper;

import jmu.pojo.Airport;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AirportMapper {
    List<Airport> selectAllAirports();          //返回所有与机场

    Airport queryAirportByID(String airportID);    //通过ID查找机场

    int deleteAirportByID(String airportID);    //通过ID删除机场

    int insertAirport(Airport airport);         //插入机场

    int updateAirport(Airport airport);         //修改机场
}