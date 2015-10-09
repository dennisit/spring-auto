package com.github.yingzhuo.spring.auto.baidulbs.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public class PlaceSuggestion implements Serializable {

    private String name;
    private Double x;
    private Double y;
    private String city;
    private String district;

    public PlaceSuggestion() {
        super();
    }

    public PlaceSuggestion(String name, Double x, Double y, String city, String district) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.city = city;
        this.district = district;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
