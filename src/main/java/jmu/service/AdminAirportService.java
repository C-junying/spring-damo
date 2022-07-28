package jmu.service;

import com.github.pagehelper.PageInfo;
import jmu.pojo.Airport;

import java.util.List;

public interface AdminAirportService {
    PageInfo<Airport> selectAllAirports(Integer currentPage, Integer pageSize);   //返回所有机场

    void airportEdit(Airport airport);  //编辑机场
    void airportDelete(Airport airport);  //删除机场
    void addAirport(Airport airport);       //增加机场
    PageInfo<Airport> airportSearch(Airport airport,Integer currentPage, Integer pageSize);  //查找机场
    List<Airport> queryAllAirports();   //返回所有机场
}
