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
public class BaiduLBSConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaiduLBSConfiguration.class);

    @Autowired
    private ConfigBean configBean;

    public BaiduLBSConfiguration() {
        LOGGER.debug("spring-auto: '{}' enabled.", BaiduLBSConfiguration.class.getSimpleName());
    }

    @Bean
    public BaiduLbsService baiduLbsService() {
        BaiduLbsService bean = new BaiduLbsService();
        bean.setAk(configBean.getAk());
        return bean;
    }

}
