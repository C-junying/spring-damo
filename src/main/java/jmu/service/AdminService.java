package jmu.service;

import com.github.pagehelper.PageInfo;
import jmu.pojo.*;

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

    void addCity(City city);                //增加城市
    void addAirport(Airport airport);       //增加机场
    void addTerminal(Terminal terminal);    //增加航站楼
    void addFlight(OnFlight flight);        //增加航班

    List<City> queryAllCities();    //返回所有城市
    List<Airport> queryAllAirports();   //返回所有机场
    List<AircraftType> queryAllAircraftTypes();   //返回所有机型

}
