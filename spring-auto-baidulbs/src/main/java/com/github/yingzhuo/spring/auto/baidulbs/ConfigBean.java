package com.github.yingzhuo.spring.auto.baidulbs;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

import java.io.Serializable;

@ConfigurationProperties(prefix = "spring.auto.baidulbs")
public class ConfigBean implements InitializingBean, Serializable {

    private String ak;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(ak);
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

}
