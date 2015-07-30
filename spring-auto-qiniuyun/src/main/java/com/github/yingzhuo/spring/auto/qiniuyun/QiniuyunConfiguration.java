package com.github.yingzhuo.spring.auto.qiniuyun;

import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;


@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties(ConfigBean.class)
@ConditionalOnClass({BucketManager.class, UploadManager.class})
public class QiniuyunConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(QiniuyunConfiguration.class);

    @Autowired
    private ConfigBean configBean;

    public QiniuyunConfiguration() {
        LOGGER.debug("spring-auto: '{}' enabled.", QiniuyunConfiguration.class.getSimpleName());
    }

    @Bean
    public QiniuyunService qiniuyunService() {
        QiniuyunService bean = new QiniuyunService();
        bean.setAk(configBean.getAccessKey());
        bean.setSk(configBean.getSecretKey());
        bean.setDomain(configBean.getDomain());
        bean.setDefaultBucket(configBean.getDefaultBucket());
        return bean;
    }

}
