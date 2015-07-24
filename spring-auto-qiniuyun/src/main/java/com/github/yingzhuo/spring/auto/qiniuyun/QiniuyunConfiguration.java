package com.github.yingzhuo.spring.auto.qiniuyun;

import com.qiniu.storage.BucketManager;
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
@ComponentScan("com.github.yingzhuo.spring.auto.qiniuyun")
public class QiniuyunConfiguration {

    @Autowired(required = false)
    private ConfigBean configBean;

    @Bean
    @ConditionalOnClass(BucketManager.class)
    public QiniuyunService qiniuyunService() {
        QiniuyunService bean = new QiniuyunService();
        bean.setAk(configBean.getAccessKey());
        bean.setSk(configBean.getSecretKey());
        bean.setDomain(configBean.getDomain());
        bean.setDefaultBucket(configBean.getDefaultBucket());
        return bean;
    }

}
