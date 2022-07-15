package jmu.mapper;

import jmu.pojo.Passenger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class PassengerMapperTest {
    @Autowired
    PassengerMapper passengerMapper;
    @Test
    void deleteByID() {
        if(passengerMapper.deleteByID("520221200112252001","520221200112252029")>0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
    }

    @Test
    void insertPassenger() {
        Passenger passenger = new Passenger("520221200112252001","520221200112252029","去玩儿","女","12345678912");
        if(passengerMapper.insertPassenger(passenger)>0){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }
    }

    @Test
    void selectByID() {
        System.out.println(passengerMapper.selectByID("520221200112252001","520221200112252001"));
    }

    @Test
    void selectUserByID() {
        System.out.println(passengerMapper.selectUserByID("520221200112252001"));
    }

    @Test
    void updatePassenger() {
        Passenger passenger = new Passenger("520221200112252001","520221200112252029","无所儿","男","14725836996");
        if(passengerMapper.updatePassenger(passenger)>0){
            System.out.println("更新成功");
        }else{
            System.out.println("更新失败");
        }
    }
}