package com.github.yingzhuo.spring.auto.jdbm2;

import jdbm.RecordManager;
import jdbm.RecordManagerFactory;
import org.springframework.beans.factory.FactoryBean;

import java.io.Closeable;
import java.io.IOException;


public class RecordManagerFactoryBean implements FactoryBean<RecordManager>, Closeable {

    private RecordManager lazyInit = null;

    private String filename;

    public RecordManagerFactoryBean(String filename) {
        this.filename = filename;
    }

    @Override
    public RecordManager getObject() throws Exception {
        if (lazyInit == null) {
            lazyInit = RecordManagerFactory.createRecordManager(filename);
        }
        return lazyInit;
    }

    @Override
    public void close() throws IOException {
        if (lazyInit != null) {
            lazyInit.close();
        }
    }

    @Override
    public Class<?> getObjectType() {
        return RecordManager.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
