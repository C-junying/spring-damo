package jmu.service;

import com.github.pagehelper.PageInfo;
import jmu.pojo.City;

import java.util.List;

public interface AdminCityService {
    PageInfo<City> selectAllCities(Integer currentPage, Integer pageSize);   //返回所有城市

    void cityEdit(City city);  //编辑城市
    void cityDelete(City city);  //删除城市
    void addCity(City city);                //增加城市
    PageInfo<City> citySearch(City city,Integer currentPage, Integer pageSize);  //查找城市
    List<City> queryAllCities();    //返回所有城市
}
