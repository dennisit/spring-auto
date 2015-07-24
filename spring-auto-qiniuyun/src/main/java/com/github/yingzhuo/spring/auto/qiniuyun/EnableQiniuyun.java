package com.github.yingzhuo.spring.auto.qiniuyun;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(QiniuyunConfiguration.class)
public @interface EnableQiniuyun {
}