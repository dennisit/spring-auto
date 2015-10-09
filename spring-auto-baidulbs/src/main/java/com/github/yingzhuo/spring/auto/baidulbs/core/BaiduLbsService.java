package com.github.yingzhuo.spring.auto.baidulbs.core;

import com.github.yingzhuo.spring.auto.baidulbs.domain.PlaceResult;
import com.github.yingzhuo.spring.auto.baidulbs.domain.PlaceSuggestion;

import java.util.List;

public interface BaiduLbsService {

    /**
     * 根据城市检索地点
     *
     * @param query    检索关键字 如: 银行,饭店等
     * @param region   城市名称 如: 北京,上海等
     * @param pageNum  页码(从0开始)
     * @param pageSize 每页条目数
     * @return 结果
     */
    public List<PlaceResult> searchPlace(String query, String region, int pageNum, int pageSize);

    /**
     * 在方形区域检索地点
     *
     * @param query    检索关键字 如: 银行,饭店等
     * @param x1       左上经度
     * @param y1       左上纬度
     * @param x2       右下经度
     * @param y2       右下纬度
     * @param pageNum  页码(从0开始)
     * @param pageSize 每页条目数
     * @return 结果
     */
    public List<PlaceResult> searchPlace(String query, double x1, double y1, double x2, double y2, int pageNum, int pageSize);

    /**
     * 在圆形区域内检索地点
     *
     * @param query    检索关键字 如: 银行,饭店等
     * @param x        圆心经度
     * @param y        圆心纬度
     * @param radius   半径(米)
     * @param pageNum  页码(从0开始)
     * @param pageSize 每页条目数
     * @return 结果
     */
    public List<PlaceResult> searchPlace(String query, double x, double y, int radius, int pageNum, int pageSize);

    public List<PlaceSuggestion> searchPlaceSuggestion(String query, String region);
}
