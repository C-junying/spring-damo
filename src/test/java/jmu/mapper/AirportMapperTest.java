package jmu.mapper;

import jmu.pojo.Airport;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AirportMapperTest {
    @Autowired
    AirportMapper airportMapper;
    @Test
    void selectAllAirports() {
        List<Airport> list = airportMapper.selectAllAirports();
        System.out.println(list);
    }

    @Test
    void queryAirportByID(){
        Airport airport = airportMapper.queryAirportByID("XMN");
        System.out.println(airport);
    }
    @Test
    void deleteAirportByID() {
        if(airportMapper.deleteAirportByID("023")>0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
        System.out.println(airportMapper.queryAirportByID("023"));
    }

    @Test
    void insertAirport() {
        Airport airport = new Airport("023","100000","君影机场",null);
        if(airportMapper.insertAirport(airport)>0){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }
        System.out.println(airportMapper.queryAirportByID(airport.getAirportID()));
    }

    @Test
    void updateAirport() {
        Airport airport = new Airport("023","100000","君影神农机场",null);
        if(airportMapper.updateAirport(airport)>0){
            System.out.println("更新成功");
        }else{
            System.out.println("更新失败");
        }
        System.out.println(airportMapper.queryAirportByID(airport.getAirportID()));
    }
}