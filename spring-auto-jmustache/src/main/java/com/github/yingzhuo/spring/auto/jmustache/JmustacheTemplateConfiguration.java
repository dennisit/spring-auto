package com.github.yingzhuo.spring.auto.jmustache;

import com.samskivert.mustache.Mustache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties(ConfigBean.class)
@ConditionalOnClass(Mustache.class)
@ConditionalOnProperty(name = "spring.auto.jmustache.enabled", havingValue = "true", matchIfMissing = false)
public class JmustacheTemplateConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(JmustacheTemplateConfiguration.class);

    @Autowired
    private ConfigBean configBean;

    public JmustacheTemplateConfiguration() {
        LOGGER.debug("spring-auto: '{}' enabled.", JmustacheTemplateConfiguration.class.getSimpleName());
    }

    @Bean
    @ConditionalOnMissingBean(value = JmustacheTemplate.class)
    public JmustacheTemplate jmustacheTemplate() {
        JmustacheTemplate bean = new JmustacheTemplate();
        bean.setEncoding(configBean.getEncoding());
        bean.setPrefix(configBean.getPrefix());
        bean.setSuffix(configBean.getSuffix());
        return bean;
    }

}
