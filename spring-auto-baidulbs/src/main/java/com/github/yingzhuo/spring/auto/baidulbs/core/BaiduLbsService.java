package com.github.yingzhuo.spring.auto.baidulbs.core;

import com.github.yingzhuo.spring.auto.baidulbs.domain.PlaceResult;

import java.util.List;

public interface BaiduLbsService {

    public List<PlaceResult> searchPlace(String query, String region, int pageNum, int pageSize);

}
