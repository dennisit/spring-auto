package com.github.yingzhuo.spring.auto.datasource.composite;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceConfig {

    public String value();
    
}