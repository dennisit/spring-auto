package com.github.yingzhuo.spring.auto;

import com.github.yingzhuo.spring.auto.qiniuyun.EnableQiniuyun;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

//@EnableJmustacheTemplate
//@EnableMybatis

@EnableQiniuyun
public class Boot {

    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }

}
