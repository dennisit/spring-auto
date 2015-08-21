package com.github.yingzhuo.spring.auto.datasource.composite;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.List;


public interface CompositeDataSourceConfigSupport extends Serializable {

    public DataSource getMaster();

    public List<DataSource> getSlaves();

    public String getInitMethodName();

    public String getDestoryMethodName();

}
