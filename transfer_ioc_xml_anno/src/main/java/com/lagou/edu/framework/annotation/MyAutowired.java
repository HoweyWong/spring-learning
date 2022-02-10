package com.lagou.edu.framework.annotation;

import java.lang.annotation.*;

/**
 * @Description TODO
 * @Author huanghao
 * @Date 2021-4-20
 * @Version 1.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAutowired {
    String value() default "";
}
