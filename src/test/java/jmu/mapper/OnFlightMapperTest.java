package jmu.mapper;

import jmu.pojo.OnFlight;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class OnFlightMapperTest {
    @Autowired
    OnFlightMapper onFlightMapper;
    @Test
    void deleteByIDTime() throws ParseException {
        if(onFlightMapper.deleteByIDTime("CA4789"
        ,new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2022-6-1 14:00:00"))>0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
    }

    @Test
    void insertOnFlight() throws ParseException{

        OnFlight onFlight = new OnFlight("CA4789","73B","XMN2022080114","空闲"
                ,new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2022-8-1 14:00:00"),
                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2022-8-1 14:00:00"),
                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2022-8-1 17:00:00"),
                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2022-8-1 17:00:00"),
                1,null);
        if(onFlightMapper.insertOnFlight(onFlight)>0){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }
    }

    @Test
    void selectByIDTime() throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2022-7-28 14:00:00");
        OnFlight onFlight = onFlightMapper.selectByIDTime("CA1456",date);
        System.out.println(onFlight);
    }

    @Test
    void selectAllOnFlights() {
        List<OnFlight> list = onFlightMapper.selectAllOnFlights();
        System.out.println(list);
    }

    @Test
    void updateOnFlight() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse("2022-8-1 14:00:00");
        SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHH");
        String str = "XMN"+simple.format(date);
        OnFlight onFlight = new OnFlight("CA4789","73B",str,"空闲"
                ,simpleDateFormat.parse("2022-6-1 14:00:00"),
                simpleDateFormat.parse("2022-6-1 14:00:00"),
                simpleDateFormat.parse("2022-6-1 17:00:00"),
                simpleDateFormat.parse("2022-6-1 17:00:00"),
                3,null);
        System.out.println(onFlight);
        if(onFlightMapper.updateOnFlight(onFlight,"CA4789",date)>0){
            System.out.println("更新成功");
        }else{
            System.out.println("更新失败");
        }
        //日期转为字符串
//        Date date = simpleDateFormat.parse("2022-8-1 14:00:00");
//        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String str = simple.format(date);
//        System.out.println(str);
    }
    @Test
    void queryOnfligthAndTicket(){
        List<OnFlight> list = onFlightMapper.queryOnfligthAndTicket("厦门市","上海市");
        System.out.println(list);
    }
}