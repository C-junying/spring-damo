package jmu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jmu.mapper.AirportMapper;
import jmu.pojo.Airport;
import jmu.service.AdminAirportService;
import jmu.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminAirportServiceImpl implements AdminAirportService {
    @Autowired
    private AirportMapper airportMapper;
    @Override
    public PageInfo<Airport> selectAllAirports(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<Airport> list =  airportMapper.selectAllAirports();
        PageInfo<Airport> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void airportEdit(Airport airport) {
        Airport result = airportMapper.queryAirportByID(airport.getAirportID());
        if(result == null){
            throw new AirportNotFoundException("机场不存在异常");
        }
        Integer rows =  airportMapper.updateAirport(airport);
        if(rows!=1){
            throw new AirportUpdateException("更新机场时产生未知异常");
        }
    }

    @Override
    public void airportDelete(Airport airport) {
        Airport result = airportMapper.queryAirportByID(airport.getAirportID());
        if(result == null){
            throw new AirportNotFoundException("机场不存在异常");
        }
        Integer rows = airportMapper.deleteAirportByID(airport.getAirportID());
        if(rows!=1){
            throw new CityDeleteException("删除机场时产生未知异常");
        }
    }
    @Override
    public void addAirport(Airport airport) {
        Airport result = airportMapper.queryAirportByID(airport.getAirportID());
        if(result!=null){
            throw new AirportIDDuplicateException("机场已存在");
        }
        Integer rows = airportMapper.insertAirport(airport);
        if(rows!=1){
            throw new InsertException("插入机场时产生未知异常");
        }
    }
    @Override
    public PageInfo<Airport> airportSearch(Airport airport, Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<Airport> list =  airportMapper.airportSearch(airport);
        PageInfo<Airport> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    @Override
    public List<Airport> queryAllAirports() {
        return airportMapper.selectAllAirports();
    }
}
