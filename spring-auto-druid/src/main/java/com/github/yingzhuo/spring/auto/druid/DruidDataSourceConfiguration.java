package com.github.yingzhuo.spring.auto.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
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
public class DruidDataSourceConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(DruidDataSourceConfiguration.class);

    @Autowired
    private ConfigBean configBean;

    @Bean
    @Primary
    @ConditionalOnClass(DruidDataSource.class)
    @ConditionalOnProperty(name = "spring.auto.druid.primary", havingValue = "true", matchIfMissing = false)
    public DataSource primaryDataSource() {
        LOGGER.debug("create druid datasource' instance. (primary)");
        return doCreateDataSource();
    }

    @Bean
    @ConditionalOnClass(DruidDataSource.class)
    @ConditionalOnProperty(name = "spring.auto.druid.primary", havingValue = "false", matchIfMissing = true)
    public DataSource nonPrimaryDataSource() {
        LOGGER.debug("create druid datasource' instance.");
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
