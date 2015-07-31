package com.github.yingzhuo.spring.auto.chufasms;

import org.apache.http.client.HttpClient;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConditionalOnClass({HttpClient.class, Element.class})
@EnableConfigurationProperties(ConfigBean.class)
@ConditionalOnProperty(name = "spring.auto.chufa.sms.enabled", havingValue = "true", matchIfMissing = false)
public class ChufaSmsConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChufaSmsConfiguration.class);

    @Autowired
    private ConfigBean configBean;

    public ChufaSmsConfiguration() {
        LOGGER.debug("spring-auto: '{}' enabled.", ChufaSmsConfiguration.class.getSimpleName());
    }

    @Bean
    @ConditionalOnMissingBean(ChufaService.class)
    public ChufaService chufaService() {
        return new ChufaService();
    }
}
