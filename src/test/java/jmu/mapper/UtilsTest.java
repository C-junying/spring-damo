package jmu.mapper;

import jmu.utils.UUIDUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsTest {

    @Test
    public void UUIDUtilsTest(){
        System.out.println("默认UUID："+ UUIDUtils.getUUID());
        System.out.println("指定UUID长度："+UUIDUtils.getUUID(16));
    }
}
