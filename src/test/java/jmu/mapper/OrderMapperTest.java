package jmu.mapper;

import jmu.pojo.Order;
import jmu.utils.UUIDUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        Order order1 = new Order(UUIDUtils.getUUID(16),"520221200112252001",200,"已支付");
        Order order2 = new Order(UUIDUtils.getUUID(16),"520221200112252001",200,"已支付");
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
        Order order = new Order("7fce75c558ac4640","520221200112252001",500,"未支付");
        if(orderMapper.updateOrder(order)>0){
            System.out.println("更新成功");
        }else{
            System.out.println("更新失败");
        }
    }
}