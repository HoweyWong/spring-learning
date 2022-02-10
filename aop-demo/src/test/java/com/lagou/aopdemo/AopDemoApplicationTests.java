package com.lagou.aopdemo;

import com.lagou.aopdemo.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AopDemoApplicationTests {
    @Autowired
    private TestService testService;
    @Test
    void contextLoads() {
        testService.eatCarrot();
        testService.eatCabbage();
    }

}
