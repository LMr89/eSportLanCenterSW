package com.g4.dev.esportlancentersw;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@SpringBootTest
class ESportLanCenterSwApplicationTests {

    @Test
    void contextLoads() {
        Calendar cal = Calendar.getInstance();
        System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(cal.getTime()));
        cal.add(Calendar.MINUTE, 180);
        System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(cal.getTime()));

    }

}
