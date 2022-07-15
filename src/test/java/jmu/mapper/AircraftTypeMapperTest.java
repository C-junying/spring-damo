package jmu.mapper;

import jmu.pojo.AircraftType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class AircraftTypeMapperTest {

    @Autowired
    AircraftTypeMapper aircraftTypeMapper;
    @Test
    void selectAll() {
        Map<String, AircraftType> map = aircraftTypeMapper.selectAll();
        System.out.println(map);
    }
}