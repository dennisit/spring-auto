package com.github.yingzhuo.spring.auto.datasource;


public final class DataSourceRemoter {

    private static final DataSourceRemoter DATA_SOURCE_REMOTER = new DataSourceRemoter();

    public static DataSourceRemoter getInstance() {
        return DATA_SOURCE_REMOTER;
    }

    private DataSourceRemoter() {
        super();
    }

    private ThreadLocal<String> holder = new ThreadLocal<>();

    public String get() {
        return holder.get();
    }

    void set(String name) {
        holder.set(name);
    }
}
