package com.github.yingzhuo.spring.auto.datasource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@SuppressWarnings("all")
public final class CompositeDataSource implements NamedDataSource, InitializingBean {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CompositeDataSource.class);
    private final Map<String, DataSource> namedDataSources = new HashMap<>();
    private String defaultDataSourceName;
    private String initMethod;
    private String destoryMethod;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(defaultDataSourceName);
        Assert.isTrue(!namedDataSources.isEmpty());
        Assert.isTrue(null != namedDataSources.get(defaultDataSourceName));
    }

    public void init() throws Exception {
        if (StringUtils.hasLength(initMethod)) {
            for (DataSource ds : this.namedDataSources.values()) {
                reflectionInvocateMethod(ds, initMethod);
            }
        }
    }

    public void close() throws Exception {
        if (StringUtils.hasLength(destoryMethod)) {
            for (DataSource ds : this.namedDataSources.values()) {
                reflectionInvocateMethod(ds, destoryMethod);
            }
        }
    }

    public void reflectionInvocateMethod(Object target, String methodName) throws Exception {
        Method method = target.getClass().getMethod(methodName);
        if (!method.isAccessible()) {
            method.setAccessible(true);
        }
        method.invoke(target, null);
    }

    public CompositeDataSource() {
        super();
    }

    public CompositeDataSource add(NamedDataSource namedDataSource) {
        this.namedDataSources.put(namedDataSource.getName(), namedDataSource.getDataSource());
        return this;
    }

    public CompositeDataSource add(NamedDataSource... namedDataSourceArray) {
        for (NamedDataSource namedDataSource : namedDataSourceArray) {
            add(namedDataSource);
        }
        return this;
    }

    @Override
    public String getName() {
        return CompositeDataSource.class.getName();
    }

    @Override
    public DataSource getDataSource() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getEffectDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getEffectDataSource().getConnection(username, password);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return getEffectDataSource().getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        getEffectDataSource().setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        getEffectDataSource().setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return getEffectDataSource().getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return getEffectDataSource().getParentLogger();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return getEffectDataSource().unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return getEffectDataSource().isWrapperFor(iface);
    }

    private DataSource getEffectDataSource() {
        if (namedDataSources.size() == 1) {
            return namedDataSources.values().iterator().next();
        }

        DataSourceRemoter remoter = DataSourceRemoter.getInstance();
        DataSource dataSource = namedDataSources.get(remoter.get());
        if (dataSource == null) {
            dataSource = namedDataSources.get(defaultDataSourceName);
        }
        return dataSource;
    }

    public String getDefaultDataSourceName() {
        return defaultDataSourceName;
    }

    public void setDefaultDataSourceName(String defaultDataSourceName) {
        this.defaultDataSourceName = defaultDataSourceName;
    }

    public String getInitMethod() {
        return initMethod;
    }

    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }

    public String getDestoryMethod() {
        return destoryMethod;
    }

    public void setDestoryMethod(String destoryMethod) {
        this.destoryMethod = destoryMethod;
    }
}
