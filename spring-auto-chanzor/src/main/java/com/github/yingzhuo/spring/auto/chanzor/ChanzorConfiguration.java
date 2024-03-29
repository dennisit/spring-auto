package com.github.yingzhuo.spring.auto.chanzor;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties(ConfigBean.class)
@ConditionalOnClass(Jsoup.class)
@ConditionalOnProperty(name = "spring.auto.chanzor.enabled", havingValue = "true", matchIfMissing = false)
public class ChanzorConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChanzorConfiguration.class);

    public ChanzorConfiguration() {
        LOGGER.debug("spring-auto: '{}' enabled.", ChanzorConfiguration.class.getSimpleName());
    }

    @Bean
    public ChanzorService chanzorService(ConfigBean configBean) {
        ChanzorService bean = new ChanzorService();
        bean.setAccount(configBean.getAccount());
        bean.setPassword(configBean.getPassword());
        return bean;
    }
}
