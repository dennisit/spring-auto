package com.github.yingzhuo.spring.auto.mybatis;

import com.github.yingzhuo.spring.auto.mybatis.config.MyBatisConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MyBatisConfiguration.class)
public @interface EnableMybatis {
}
