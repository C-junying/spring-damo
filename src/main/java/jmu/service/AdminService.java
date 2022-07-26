package jmu.service;

import com.github.pagehelper.PageInfo;
import jmu.pojo.Airport;
import jmu.pojo.City;
import jmu.pojo.User;

import java.util.List;
import java.util.Map;

public interface AdminService {

    PageInfo<City> selectAllCities(Integer currentPage, Integer pageSize);   //返回所有城市

    void cityEdit(City city);  //编辑城市
    void cityDelete(City city);  //删除城市
    PageInfo<City> citySearch(City city,Integer currentPage, Integer pageSize);  //查找城市

    PageInfo<Airport> selectAllAirports(Integer currentPage, Integer pageSize);   //返回所有机场

    void airportEdit(Airport airport);  //编辑机场
    void airportDelete(Airport airport);  //删除机场
    PageInfo<Airport> airportSearch(Airport airport,Integer currentPage, Integer pageSize);  //查找机场

    PageInfo<User> selectAllUser(Integer currentPage, Integer pageSize);   //返回所有用户
    void userDelete(User user);  //删除机场
    PageInfo<User> usertSearch(User user,Integer currentPage, Integer pageSize);  //查找用户
}
