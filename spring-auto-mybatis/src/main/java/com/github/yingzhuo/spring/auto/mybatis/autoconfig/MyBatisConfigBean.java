package com.github.yingzhuo.spring.auto.mybatis.autoconfig;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

@ConfigurationProperties(prefix = "spring.auto.mybatis")
public class MyBatisConfigBean implements InitializingBean {

    private Resource configLocation = new ClassPathResource("mybatis.cnf.xml");
    private boolean failFast = true;
    private String executorType = "SIMPLE";
    private boolean useExceptionTranslator = true;

    public MyBatisConfigBean() {
        super();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!configLocation.exists()) {
            throw new IOException(configLocation + " NOT found.");
        }
    }

    public Resource getConfigLocation() {
        return configLocation;
    }

    public void setConfigLocation(Resource configLocation) {
        this.configLocation = configLocation;
    }

    public boolean isFailFast() {
        return failFast;
    }

    public void setFailFast(boolean failFast) {
        this.failFast = failFast;
    }

    public String getExecutorType() {
        return executorType;
    }

    public void setExecutorType(String executorType) {
        this.executorType = executorType;
    }

    public boolean isUseExceptionTranslator() {
        return useExceptionTranslator;
    }

    public void setUseExceptionTranslator(boolean useExceptionTranslator) {
        this.useExceptionTranslator = useExceptionTranslator;
    }
}
