package com.github.yingzhuo.spring.auto.qiniuyun;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

import java.io.Serializable;

@ConfigurationProperties(prefix = "spring.auto.qiniuyun")
public class ConfigBean implements Serializable, InitializingBean {

    private String bucket;
    private String accessKey;
    private String secretKey;
    private String urlPrefix;

    public ConfigBean() {
        super();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(bucket, "you should config 'spring.auto.qiniuyun.bucket'.");
        Assert.hasText(accessKey, "you should config 'spring.auto.qiniuyun.access-key'.");
        Assert.hasText(secretKey, "you should config 'spring.auto.qiniuyun.secret-key'.");
        Assert.hasText(urlPrefix, "you should config 'spring.auto.qiniuyun.url-prefix'.");

        if (!urlPrefix.endsWith("/")) {
            urlPrefix += "/";
        }
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
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

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

}
