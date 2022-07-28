package jmu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jmu.mapper.CityMapper;
import jmu.pojo.City;
import jmu.service.AdminCityService;
import jmu.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class AdminCityServiceImpl implements AdminCityService {
    @Autowired
    private CityMapper cityMapper;
    @Override
    public PageInfo<City> selectAllCities(Integer currentPage,Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<City> list =  cityMapper.selectAllCities();
        PageInfo<City> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void cityEdit(City city) {
        City result = cityMapper.queryCityByID(city.getCityID());
        if(result == null){
            throw new CityNotFoundException("城市不存在异常");
        }
        Integer rows =  cityMapper.updateCity(city);
        if(rows!=1){
            throw new CityUpdateException("更新城市时产生未知异常");
        }
    }
    @Override
    public void addCity(City city) {
        City result = cityMapper.queryCityByID(city.getCityID());
        if(result!=null){
            throw new CityIDDuplicateException("城市已存在");
        }
        Integer rows = cityMapper.insertCity(city);
        if(rows!=1){
            throw new InsertException("插入城市时产生未知异常");
        }
    }
    @Override
    public void cityDelete(City city) {
        City result = cityMapper.queryCityByID(city.getCityID());
        if(result == null){
            throw new CityNotFoundException("城市不存在异常");
        }
        Integer rows = cityMapper.deleteCityByID(city.getCityID());
        if(rows!=1){
            throw new CityDeleteException("删除城市时产生未知异常");
        }
    }

    @Override
    public PageInfo<City> citySearch(City city,Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<City> list =  cityMapper.queryCityByNameAndID(city);
        PageInfo<City> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    @Override
    public List<City> queryAllCities() {
        return cityMapper.selectAllCities();
    }
}
