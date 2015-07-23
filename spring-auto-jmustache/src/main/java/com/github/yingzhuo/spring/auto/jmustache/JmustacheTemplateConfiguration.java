package com.github.yingzhuo.spring.auto.jmustache;

import com.samskivert.mustache.Mustache;
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

    @Autowired(required = false)
    private ConfigBean configBean;

    @Bean
    @ConditionalOnClass({Mustache.class, ConfigBean.class})
    public JmustacheTemplate jmustacheTemplate() {
        JmustacheTemplate bean = new JmustacheTemplate();
        bean.setEncoding(configBean.getEncoding());
        bean.setPrefix(configBean.getPrefix());
        bean.setSuffix(configBean.getSuffix());
        return bean;
    }
}
