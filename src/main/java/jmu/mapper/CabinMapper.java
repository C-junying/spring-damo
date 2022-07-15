package jmu.mapper;

import jmu.pojo.Cabin;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface CabinMapper {
    int deleteByID(int cabinID);        //通过ID删除舱位

    int insertCabin(Cabin cabin);            //插入舱位

    Cabin selectByID(int cabinID);      //根据ID查询舱位

    @MapKey("cabin_id")
    Map<Integer,Cabin> selectAllCabins();   //返回所有舱位

    int updateCabin(Cabin cabin);                //修改舱位名
}
