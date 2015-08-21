package com.github.yingzhuo.spring.auto.datasource.composite.tool;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@ConfigurationProperties(prefix = "spring.auto.composite.datasource")
public class DataSourceConfigBean {

    private String masterDriverClass;
    private String masterUrl;
    private String masterUsername;
    private String masterPassword;
    private List<String> slaveDriverClasses = new ArrayList<>();
    private List<String> slaveUrls          = new ArrayList<>();
    private List<String> slaveUsernames     = new ArrayList<>();
    private List<String> slavePasswords     = new ArrayList<>();

    public DataSourceCore getMasterDataSourceCore() {
        return new DataSourceCore(masterDriverClass, masterUrl, masterUsername, masterPassword);
    }

    public Iterator<DataSourceCore> getSlaveDataSourceCoreIterator() {
        int size = -1;
        size = Math.max(size, slaveDriverClasses.size());
        size = Math.max(size, slaveUrls.size());
        size = Math.max(size, slaveUsernames.size());
        size = Math.max(size, slavePasswords.size());

        List<DataSourceCore> list = new ArrayList<>(size);
        for (int i = 0; i < size; i ++) {
            list.add(new DataSourceCore(
                    getFromListQuietly(slaveDriverClasses, i),
                    getFromListQuietly(slaveUrls, i),
                    getFromListQuietly(slaveUsernames, i),
                    getFromListQuietly(slavePasswords, i)));
        }

        return Collections.unmodifiableList(list).iterator();
    }

    private String getFromListQuietly(List<String> list, int index) {
        try {
            return list.get(index);
        } catch (IndexOutOfBoundsException ignored) {
            return null;
        }
    }

    public String getMasterUrl() {
        return masterUrl;
    }

    public void setMasterUrl(String masterUrl) {
        this.masterUrl = masterUrl;
    }

    public String getMasterDriverClass() {
        return masterDriverClass;
    }

    public void setMasterDriverClass(String masterDriverClass) {
        this.masterDriverClass = masterDriverClass;
    }

    public String getMasterUsername() {
        return masterUsername;
    }

    public void setMasterUsername(String masterUsername) {
        this.masterUsername = masterUsername;
    }

    public String getMasterPassword() {
        return masterPassword;
    }

    public void setMasterPassword(String masterPassword) {
        this.masterPassword = masterPassword;
    }

    public List<String> getSlaveUrls() {
        return slaveUrls;
    }

    public void setSlaveUrls(List<String> slaveUrls) {
        this.slaveUrls = slaveUrls;
    }

    public List<String> getSlaveDriverClasses() {
        return slaveDriverClasses;
    }

    public void setSlaveDriverClasses(List<String> slaveDriverClasses) {
        this.slaveDriverClasses = slaveDriverClasses;
    }

    public List<String> getSlaveUsernames() {
        return slaveUsernames;
    }

    public void setSlaveUsernames(List<String> slaveUsernames) {
        this.slaveUsernames = slaveUsernames;
    }

    public List<String> getSlavePasswords() {
        return slavePasswords;
    }

    public void setSlavePasswords(List<String> slavePasswords) {
        this.slavePasswords = slavePasswords;
    }
}
