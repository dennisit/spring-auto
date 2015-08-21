package com.github.yingzhuo.spring.auto.datasource.composite;

import com.github.yingzhuo.spring.auto.datasource.NamedDataSource;
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
public class CompositeDataSourceConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompositeDataSourceConfiguration.class);

    private static final String MASTER = "MASTER";
    private static final String SLAVE  = "SLAVE";

    @Autowired
    private CompositeDataSourceConfigSupport configSupport;

    public CompositeDataSourceConfiguration() {
        LOGGER.debug("spring-auto: '{}' enabled.", CompositeDataSourceConfiguration.class.getSimpleName());
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
            bean.add(NamedDataSource.of(MASTER, master));
        }

        List<DataSource> slaves = configSupport.getSlaves();
        if (slaves != null && !slaves.isEmpty()) {
            for (int i = 0; i < slaves.size(); i++) {
                bean.add(NamedDataSource.of(SLAVE + i, slaves.get(i)));
            }
        }
        return bean;
    }

}
