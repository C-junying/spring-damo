package jmu.mapper;

import jmu.pojo.AirTicket;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AirTicketMapperTest {
    @Autowired
    AirTicketMapper airTicketMapper;
    @Test
    void deleteByID() throws ParseException {
        if(airTicketMapper.deleteByIDTime("520221200112252001",new SimpleDateFormat("yyyy-MM-ss HH:mm:ss").parse("2022-3-4 14:12:00"))>0){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }

    @Test
    void insertAirTicket() throws ParseException {
        AirTicket airTicket = new AirTicket("520221200112252001","520221200112252001","402f09848391485f","XMN2022072508",500,25,
                new SimpleDateFormat("yyyy-MM-ss HH:mm:ss").parse("2022-3-4 14:12:00"),1,null,null,null);
        AirTicket airTicket1 = new AirTicket("520221200112252001","520221200112252003","402f09848391485f","XMN2022072508",500,27,
                new SimpleDateFormat("yyyy-MM-ss HH:mm:ss").parse("2022-5-16 14:12:00"),2,null,null,null);
        if(airTicketMapper.insertAirTicket(airTicket)>0){
            System.out.println("插入成功");
        }else {
            System.out.println("插入失败");
        }
        if(airTicketMapper.insertAirTicket(airTicket1)>0){
            System.out.println("插入成功");
        }else {
            System.out.println("插入失败");
        }
    }

    @Test
    void selectByIDTime() throws ParseException {
        System.out.println(airTicketMapper.selectByIDTime("520221200112252001",new SimpleDateFormat("yyyy-MM-ss HH:mm:ss").parse("2022-5-16 14:12:00")));
    }

    @Test
    void selectAirTicketsByOrderID() {
        System.out.println(airTicketMapper.selectAirTicketsByOrderID("402f09848391485f"));
    }

    @Test
    void updateAirTicket() throws ParseException {
        AirTicket airTicket = new AirTicket("520221200112252001","520221200112252003","402f09848391485f","XMN2022072508",400,11,
                new SimpleDateFormat("yyyy-MM-ss HH:mm:ss").parse("2022-5-16 14:12:00"),2,null,null,null);
        if(airTicketMapper.updateAirTicket(airTicket)>0){
            System.out.println("更新成功");
        }else {
            System.out.println("更新失败");
        }
    }
}