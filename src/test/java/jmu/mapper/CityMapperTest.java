package jmu.mapper;

import jmu.pojo.City;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class CityMapperTest {
    @Autowired
    CityMapper cityMapper;
    @Test
    void selectAllCities() {
        List<City> list = cityMapper.selectAllCities();
        System.out.println(list);
    }
    @Test
    void queryCityByID(){
        City city = cityMapper.queryCityByID("030600");
        System.out.println(city);
    }

    @Test
    void queryCityByName(){
        List<City> city = cityMapper.queryCityByName("上海");
        System.out.println(city);
    }
    @Test
    void deleteCityByID() {
        int a = cityMapper.deleteCityByID("1234567");
        System.out.println("删除是否"+a);
        City city = cityMapper.queryCityByID("123456");
        System.out.println(city);
    }

    @Test
    void insertCity() {
        City city = new City("1234567","君影");
        cityMapper.insertCity(city);
        System.out.println(cityMapper.queryCityByID(city.getCityID()));
    }

    @Test
    void updateCity() {
        City city = new City("1234567","你好呀");
        cityMapper.updateCity(city);
        System.out.println(cityMapper.queryCityByID(city.getCityID()));
    }
}