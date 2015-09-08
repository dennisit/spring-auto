package com.github.yingzhuo.spring.auto.qiniuyun;

import com.github.yingzhuo.spring.auto.qiniuyun.core.QiniuyunManager;
import com.github.yingzhuo.spring.auto.qiniuyun.core.SimpleQiniuyunManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties(ConfigBean.class)
@ConditionalOnClass(Auth.class)
@ConditionalOnProperty(name = "spring.auto.qiniuyun.enabled", havingValue = "true", matchIfMissing = false)
public class QiniuyunConfiguration {

    private final static Logger LOGGER = LoggerFactory.getLogger(QiniuyunConfiguration.class);

    @Autowired
    private ConfigBean configBean;

    public QiniuyunConfiguration() {
        LOGGER.debug("spring-auto: '{}' enabled.", QiniuyunConfiguration.class.getSimpleName());
    }

    @Bean
    public QiniuyunManager qiniuyunUploadManager() {
        SimpleQiniuyunManager manager = new SimpleQiniuyunManager();
        manager.setAk(configBean.getAccessKey());
        manager.setSk(configBean.getSecretKey());
        manager.setBucket(configBean.getBucket());
        manager.setUrlPrefix(configBean.getUrlPrefix());
        return manager;
    }

}
