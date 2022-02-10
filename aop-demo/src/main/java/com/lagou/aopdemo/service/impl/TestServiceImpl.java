package com.lagou.aopdemo.service.impl;

import com.lagou.aopdemo.annotation.GlobalErrorCatch;
import com.lagou.aopdemo.service.TestService;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author huanghao
 * @Date 2021-11-16
 * @Version 1.0
 */
@Component
public class TestServiceImpl implements TestService {
    @Override
    @GlobalErrorCatch
    public void eatCarrot() {
        int i = 1/0;
        System.out.println("吃萝卜");
    }

    @Override
    public void eatMushroom() {
        System.out.println("吃蘑菇");
    }

    @Override
    public void eatCabbage() {
        System.out.println("吃白菜");
    }
}
