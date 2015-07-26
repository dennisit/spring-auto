package com.github.yingzhuo.spring.auto.jmustache;

import com.samskivert.mustache.Mustache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configurable
@EnableAutoConfiguration
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties(ConfigBean.class)
@ComponentScan("com.github.yingzhuo.spring.auto.jmustache")
public class JmustacheTemplateConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(JmustacheTemplateConfiguration.class);

    @Autowired
    private ConfigBean configBean;

    @Bean
    @ConditionalOnClass(Mustache.class)
    public JmustacheTemplate jmustacheTemplate() {

        LOGGER.debug("create JmustacheTemplate's instance.");

        JmustacheTemplate bean = new JmustacheTemplate();
        bean.setEncoding(configBean.getEncoding());
        bean.setPrefix(configBean.getPrefix());
        bean.setSuffix(configBean.getSuffix());
        return bean;
    }

}
