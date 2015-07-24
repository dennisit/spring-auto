package com.github.yingzhuo.spring.auto.qiniuyun;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

@ConfigurationProperties(prefix = "spring.auto.qiniuyun")
public class ConfigBean implements InitializingBean {

    private String defaultBucket;
    private String accessKey;
    private String secretKey;
    private String domain;

    public ConfigBean() {
        super();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(defaultBucket);
        Assert.hasText(accessKey);
        Assert.hasText(secretKey);
        Assert.hasText(domain);
    }

    public String getDefaultBucket() {
        return defaultBucket;
    }

    public void setDefaultBucket(String defaultBucket) {
        this.defaultBucket = defaultBucket;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
