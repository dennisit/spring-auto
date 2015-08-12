package com.github.yingzhuo.baidulbs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties(ConfigBean.class)
@ConditionalOnProperty(name = "spring.auto.baidulbs.enabled", havingValue = "true", matchIfMissing = false)
public class BaiduLbsConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaiduLbsConfiguration.class);

    @Autowired
    private ConfigBean configBean;

    public BaiduLbsConfiguration() {
        LOGGER.debug("spring-auto: '{}' enabled.", BaiduLbsConfiguration.class.getSimpleName());
    }

    @Bean
    public BaiduLbsService baiduLbsService() {
        SimpleBaiduLbsService bean = new SimpleBaiduLbsService();
        bean.setAk(configBean.getAk());
        return bean;
    }

}
