package com.github.yingzhuo.spring.auto.jdbm2;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@ConfigurationProperties(prefix = "spring.auto.jdbm2")
public class ConfigBean implements Serializable, InitializingBean {

    private String filename = System.getProperty("java.io.tmpdir") + "com.github.yingzhuo.spring.auto.jdbm2";

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
