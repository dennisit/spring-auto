package com.github.yingzhuo.spring.auto.jmustache;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

import java.io.Serializable;

@ConfigurationProperties(prefix = "spring.auto.jmustache")
public class ConfigBean implements Serializable, InitializingBean {

    private String prefix = "META-INF/jmustache-templates/";
    private String suffix = "";
    private String encoding = "UTF-8";

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(prefix);
        Assert.hasText(encoding);

        if (prefix.startsWith("/")) {
            prefix = prefix.substring(1);
        }

        if (!prefix.endsWith("/")) {
            prefix = prefix + "/";
        }
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
