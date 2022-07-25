package jmu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jmu.mapper.CityMapper;
import jmu.pojo.City;
import jmu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private CityMapper cityMapper;
    @Override
    public PageInfo<City> selectAllCities(Integer currentPage,Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<City> list =  cityMapper.selectAllCities();
        PageInfo<City> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
