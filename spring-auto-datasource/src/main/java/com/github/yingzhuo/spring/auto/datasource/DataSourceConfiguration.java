package com.github.yingzhuo.spring.auto.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ConditionalOnBean(CompositeDataSourceConfigSupport.class)
@ConditionalOnProperty(name = "spring.auto.composite.datasource.enabled", havingValue = "true", matchIfMissing = false)
public class DataSourceConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfiguration.class);

    private static final String MASTER = "MASTER";
    private static final String SLAVE = "SLAVE";

    @Autowired
    private CompositeDataSourceConfigSupport configSupport;

    public DataSourceConfiguration() {
        LOGGER.debug("spring-auto: '{}' enabled.", DataSourceConfiguration.class.getSimpleName());
    }

    @Primary
    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource compositeDataSource() {
        CompositeDataSource bean = new CompositeDataSource();
        bean.setDestoryMethod(configSupport.getDestoryMethodName());
        bean.setInitMethod(configSupport.getInitMethodName());
        bean.setDefaultDataSourceName(MASTER);

        DataSource master = configSupport.getMaster();
        if (master != null) {
            bean.add(NamedDataSource.of(MASTER, configSupport.getMaster()));
        }

        List<DataSource> slaveDataSourceList = configSupport.getSlaves();
        if (slaveDataSourceList != null && !slaveDataSourceList.isEmpty()) {
            for (int i = 0; i < configSupport.getSlaves().size(); i++) {
                bean.add(NamedDataSource.of(SLAVE + i, configSupport.getSlaves().get(i)));
            }
        }

        return bean;
    }
}
