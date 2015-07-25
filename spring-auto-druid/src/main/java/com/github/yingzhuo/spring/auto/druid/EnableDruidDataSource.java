package com.github.yingzhuo.spring.auto.druid;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(DataSourceConfiguration.class)
public @interface EnableDruidDataSource {
}
