package com.github.yingzhuo.spring.auto.datasource;

import org.springframework.util.Assert;

import javax.sql.DataSource;

public interface NamedDataSource extends DataSource {

    public static NamedDataSource of(final String name, final DataSource dataSource) {
        Assert.hasText(name, "数据源名称不可为null");
        Assert.notNull(dataSource, "数据源不可为null");
        return new SimpleNamedDataSource(name, dataSource);
    }

    public String getName();

    public DataSource getDataSource();
}