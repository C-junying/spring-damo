package jmu.mapper;

import jmu.utils.UUIDUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsTest {

    @Test
    public void UUIDUtilsTest(){
        System.out.println("默认UUID："+ UUIDUtils.getUUID());
        System.out.println("指定UUID长度："+UUIDUtils.getUUID(16));
    }
    @Test
    public void timeTest() throws ParseException {
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = simpleDateFormat.format(new Date());
        Date date = simpleDateFormat.parse(now);
        System.out.println(date);
    }
}
