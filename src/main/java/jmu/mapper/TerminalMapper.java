package jmu.mapper;

import jmu.pojo.Terminal;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface TerminalMapper {
    @MapKey("airport_id")
    Map<String, Terminal> selectAll();         //返回所有航站楼

    Terminal queryTerminalByTAID(String terminalID,String airportID);   //通过两ID返回航站楼

    int deleteTerminalByID(String terminalID,String airportID);     //根据ID删除航站楼

    int insertTerminal(Terminal terminal);      //插入航站楼

    int updateTerminal(Terminal terminal);      //更新航站楼
}
