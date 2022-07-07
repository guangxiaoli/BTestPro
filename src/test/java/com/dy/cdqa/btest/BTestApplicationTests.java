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
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbb");
    }


    @Test
    void contextLoads2() throws Exception {
        assertEquals("a","c","value匹配");
    }

    @Test
    void contextLoads3() throws Exception {
        assertTrue(5>3);
    }



}
