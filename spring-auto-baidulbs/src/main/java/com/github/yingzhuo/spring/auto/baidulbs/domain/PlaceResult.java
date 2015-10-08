package com.github.yingzhuo.spring.auto.baidulbs.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * 地理位置信息
 *
 * @author 应卓
 */
public class PlaceResult implements Serializable {

    private String name;
    private Double x;
    private Double y;
    private String address;
    private String telephone;

    public PlaceResult() {
        super();
    }

    public PlaceResult(String name, Double x, Double y, String address, String telephone) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.address = address;
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this).build();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
