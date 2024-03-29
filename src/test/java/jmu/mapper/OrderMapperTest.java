package jmu.mapper;

import jmu.pojo.Order;
import jmu.utils.UUIDUtils;
import jmu.vo.Flight;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderMapperTest {
    @Autowired
    OrderMapper orderMapper;
    @Test
    void deleteByID() {
        if(orderMapper.deleteByID("7fce75c558ac4640")>0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
    }

    @Test
    void insertOrder() {
        Order order1 = new Order(UUIDUtils.getUUID(16),"520221200112252001",200,"已支付",null);
        Order order2 = new Order(UUIDUtils.getUUID(16),"520221200112252001",200,"已支付",null);
        if(orderMapper.insertOrder(order1)>0){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }
        if(orderMapper.insertOrder(order2)>0){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }
    }

    @Test
    void selectByID() {
        System.out.println(orderMapper.selectByID("7fce75c558ac4640"));
    }

    @Test
    void selectByUserID() {
        System.out.println(orderMapper.selectByUserID("520221200112252001"));
    }

    @Test
    void updateOrder() {
        Order order = new Order("7fce75c558ac4640","520221200112252001",500,"未支付",null);
        if(orderMapper.updateOrder(order)>0){
            System.out.println("更新成功");
        }else{
            System.out.println("更新失败");
        }
    }
    @Test
    void queryAllUserOrder() throws ParseException {
        List<Order> list = orderMapper.queryAllUserOrder("520221200112252001");
        Flight flight = list.get(0).getAirTickets().get(0).getFlight();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = simpleDateFormat.parse("2022-8-1 14:24:50");
        Date date2 = simpleDateFormat.parse("2022-8-2 13:30:30");
        String str = DateUtil.formatTimeDifference(date2,date1);
        System.out.println(str);
        System.out.println(list);
    }
}