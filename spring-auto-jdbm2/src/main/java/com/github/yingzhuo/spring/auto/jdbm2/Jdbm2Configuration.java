package com.github.yingzhuo.spring.auto.jdbm2;

import jdbm.RecordManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(ConfigBean.class)
@ConditionalOnClass(RecordManagerFactory.class)
@ConditionalOnProperty(name = "spring.auto.jdbm2.enabled", havingValue = "true", matchIfMissing = false)
public class Jdbm2Configuration {

    private static final Logger LOGGER = LoggerFactory.getLogger(Jdbm2Configuration.class);

    @Autowired
    private ConfigBean configBean;

    public Jdbm2Configuration() {
        LOGGER.debug("spring-auto: '{}' enabled.", Jdbm2Configuration.class.getSimpleName());
    }

    @Bean(destroyMethod = "close")
    public RecordManagerFactoryBean recordManager() {
        return new RecordManagerFactoryBean(configBean.getFilename());
    }

}
