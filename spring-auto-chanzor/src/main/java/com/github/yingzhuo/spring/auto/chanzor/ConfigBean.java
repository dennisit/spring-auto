package com.github.yingzhuo.spring.auto.chanzor;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

import java.io.Serializable;

@ConfigurationProperties(prefix = "spring.auto.chanzor")
public class ConfigBean implements Serializable, InitializingBean {

    private String account;
    private String password;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(account);
        Assert.hasText(password);
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
