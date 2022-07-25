package jmu.service;

import com.github.pagehelper.PageInfo;
import jmu.pojo.City;

import java.util.List;
import java.util.Map;

public interface AdminService {

    PageInfo<City> selectAllCities(Integer currentPage, Integer pageSize);   //返回所有城市
}
