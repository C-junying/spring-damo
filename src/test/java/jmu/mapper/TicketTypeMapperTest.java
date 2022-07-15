package jmu.mapper;

import jmu.pojo.TicketType;
import jmu.vo.TicketName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TicketTypeMapperTest {

    @Autowired
    TicketTypeMapper ticketTypeMapper;
    @Test
    void deleteByID() {
        if(ticketTypeMapper.deleteByID("XMN20220801")>0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
    }

    @Test
    void insertTicketType() {
        TicketType ticketType = new TicketType("XMN20220801","AKU","T1"
                ,"BAV","T2",300,20,50,100,100);
        if(ticketTypeMapper.insertTicketType(ticketType)>0){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }
    }
    @Test
    void selectAll() {
        System.out.println(ticketTypeMapper.selectAll());
    }
    @Test
    void selectByID() {
        System.out.println(ticketTypeMapper.selectByID("XMN20220801"));

    }

    @Test
    void updateTicketType() {
        TicketType ticketType = new TicketType("XMN20220801","AKU","T1"
                ,"BAV","T2",600,20,50,100,100);
        if(ticketTypeMapper.updateTicketType(ticketType)>0){
            System.out.println("更新成功");
        }else{
            System.out.println("更新失败");
        }
    }
    @Test
    void ticketpassenger(){
        TicketName list = ticketTypeMapper.queryTicketPassenger("XMN2022072508");
        System.out.println(list);
    }
}