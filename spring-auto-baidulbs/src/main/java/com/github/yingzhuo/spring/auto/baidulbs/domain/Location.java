package com.github.yingzhuo.spring.auto.baidulbs.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Location implements Serializable {

    private String address;
    private String city;
    private Integer cityCode;
    private String district;
    private String province;
    private String street;
    private String streetNumber;
    private Double x;
    private Double y;

    public Location() {
        super();
    }

    public Location(String address, String city, Integer cityCode, String district, String province, String street, String streetNumber, Double x, Double y) {
        this.address = address;
        this.city = city;
        this.cityCode = cityCode;
        this.district = district;
        this.province = province;
        this.street = street;
        this.streetNumber = streetNumber;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).build();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
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
}
