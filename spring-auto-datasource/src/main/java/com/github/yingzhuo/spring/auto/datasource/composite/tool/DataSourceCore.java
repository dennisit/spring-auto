package com.github.yingzhuo.spring.auto.datasource.composite.tool;

import java.io.Serializable;


public class DataSourceCore implements Serializable {

    private final String driverClass;
    private final String url;
    private final String username;
    private final String password;

    public DataSourceCore(String driverClass, String url, String username, String password) {
        this.driverClass = driverClass;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "DataSourceCore{" +
                "driverClass='" + driverClass + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
