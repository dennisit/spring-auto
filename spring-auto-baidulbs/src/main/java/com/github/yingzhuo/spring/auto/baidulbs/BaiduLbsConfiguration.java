package com.github.yingzhuo.spring.auto.baidulbs;

import com.github.yingzhuo.spring.auto.baidulbs.core.BaiduLbsService;
import com.github.yingzhuo.spring.auto.baidulbs.core.SimpleLbsService;
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
@ConditionalOnProperty(name = "spring.auto.baidulbs.enabled", havingValue = "true", matchIfMissing = false)
public class BaiduLbsConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaiduLbsConfiguration.class);

    public BaiduLbsConfiguration() {
        LOGGER.debug("spring-auto: '{}' enabled.", BaiduLbsConfiguration.class.getSimpleName());
    }

    @Bean
    public BaiduLbsService baiduLbsService(ConfigBean configBean) {
        return new SimpleLbsService(configBean.getAk());
    }
}
