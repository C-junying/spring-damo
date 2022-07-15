package jmu.mapper;

import jmu.pojo.Terminal;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TerminalMapperTest {
    @Autowired
    TerminalMapper terminalMapper;
    @Test
    void selectAll() {
        System.out.println(terminalMapper.selectAll());
    }

    @Test
    void deleteTerminalByID() {
        if(terminalMapper.deleteTerminalByID("T3","YUZ")>0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
    }

    @Test
    void insertTerminal() {
        Terminal terminal = new Terminal("T3","ZHA","君影机场T3航站楼");
        if(terminalMapper.insertTerminal(terminal)>0){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }
        System.out.println(terminalMapper.queryTerminalByTAID(terminal.getTerminalID(),terminal.getAirportID()));
    }

    @Test
    void updateTerminal() {
        Terminal terminal = new Terminal("T3","YUZ","君影神农机场T3航站楼");
        if(terminalMapper.updateTerminal(terminal)>0){
            System.out.println("更新成功");
        }else{
            System.out.println("更新失败");
        }
        System.out.println(terminalMapper.queryTerminalByTAID(terminal.getTerminalID(),terminal.getAirportID()));
    }
}