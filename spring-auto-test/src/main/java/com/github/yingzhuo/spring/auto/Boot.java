package com.github.yingzhuo.spring.auto;

import com.github.yingzhuo.spring.auto.druid.EnableDruidDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@EnableDruidDataSource
public class Boot {

    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }

}
