package com.github.yingzhuo.baidulbs;

import com.github.yingzhuo.baidulbs.result.LocationInformation;

import java.util.Optional;


public interface BaiduLbsService {

    public Optional<LocationInformation> findLocationByIpAddress(String ipAddress);

}
