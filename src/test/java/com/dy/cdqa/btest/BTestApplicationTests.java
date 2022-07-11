package com.dy.cdqa.btest;

import com.dy.cdqa.btest.utils.YunxiaoUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BTestApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("测试输出一下");
    }


    @Test
    void contextLoads2() throws Exception {
        assertEquals("a","a","value匹配");
    }

    @Test
    void contextLoads3() throws Exception {
        assertTrue(1>3);
    }

    @Test
    void contextLoads4() throws Exception {
        assertTrue(1>3);
        assertEquals(100,300);
    }

}
