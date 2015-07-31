package com.github.yingzhuo.spring.auto.chufasms;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

import java.io.Serializable;

@ConfigurationProperties(prefix = "spring.auto.chufa.sms")
public class ConfigBean implements Serializable, InitializingBean {

    private String username;
    private String password;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(username);
        Assert.hasText(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
