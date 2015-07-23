package com.github.yingzhuo.spring.auto;

import com.github.yingzhuo.spring.auto.jmustache.EnableJmustacheTemplate;
import com.github.yingzhuo.spring.auto.mybatis.EnableMybatis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@EnableJmustacheTemplate
@EnableMybatis
public class Boot {

    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }

}
