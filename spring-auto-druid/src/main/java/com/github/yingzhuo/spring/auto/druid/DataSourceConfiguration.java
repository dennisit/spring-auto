package com.github.yingzhuo.spring.auto.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;

@Configurable
@EnableAutoConfiguration
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties(ConfigBean.class)
@ComponentScan("com.github.yingzhuo.spring.auto.druid")
public class DataSourceConfiguration {

    @Autowired
    private ConfigBean configBean;

    @Bean
    @Primary
    @ConditionalOnProperty(name = "spring.auto.druid.primary", havingValue = "true", matchIfMissing = false)
    public DataSource primaryDataSource() {
        return doCreateDataSource();
    }

    @Bean
    @ConditionalOnProperty(name = "spring.auto.druid.primary", havingValue = "false", matchIfMissing = true)
    public DataSource nonPrimaryDataSource() {
        return doCreateDataSource();
    }

    private DataSource doCreateDataSource() {
        DruidDataSource bean = new DruidDataSource();
        bean.setDriverClassName(configBean.getDriverClassName());
        bean.setUrl(configBean.getUrl());
        bean.setUsername(configBean.getUsername());
        bean.setPassword(configBean.getPassword());
        bean.setInitialSize(configBean.getInitialSize());
        bean.setMaxActive(configBean.getMaxActive());
        bean.setMinIdle(configBean.getMinIdle());
        bean.setPoolPreparedStatements(configBean.isPoolPreparedStatements());
        bean.setMaxOpenPreparedStatements(configBean.getMaxOpenPreparedStatements());
        return bean;
    }
}
