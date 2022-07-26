package jmu.mapper;

import jmu.pojo.City;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CityMapper {
    List<City> selectAllCities();       //返回所有城市

    City queryCityByID(String cityID);  //根据ID返回城市

    List<City> queryCityByNameAndID(City city);  //根据名字返回城市

    int deleteCityByID(String cityID);  //根据ID删除城市

    int insertCity(City city);          //插入城市

    int updateCity(City city);          //更新城市
}
