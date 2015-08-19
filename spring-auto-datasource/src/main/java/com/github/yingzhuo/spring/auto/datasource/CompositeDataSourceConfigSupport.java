package com.github.yingzhuo.spring.auto.datasource;

import javax.sql.DataSource;
import java.util.List;


public interface CompositeDataSourceConfigSupport {

    public DataSource getMaster();

    public List<DataSource> getSlaves();

    public String getInitMethodName();

    public String getDestoryMethodName();

}
