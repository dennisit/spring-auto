package com.github.yingzhuo.spring.auto.baidulbs.core;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yingzhuo.spring.auto.baidulbs.domain.Location;
import com.github.yingzhuo.spring.auto.baidulbs.domain.PlaceResult;
import com.github.yingzhuo.spring.auto.baidulbs.domain.PlaceSuggestion;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.ConstructorProperties;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SimpleBaiduLbsService implements BaiduLbsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleBaiduLbsService.class);
    private static final String SEARCH_URL = "http://api.map.baidu.com/place/v2/search";
    private static final String SEARCH_SUGGESTION_URL = "http://api.map.baidu.com/place/v2/suggestion";
    private static final String IP_2_LOCATION_URL = "http://api.map.baidu.com/location/ip";

    private final String ak;

    @ConstructorProperties({"ak"})
    public SimpleBaiduLbsService(String ak) {
        this.ak = ak;
    }

    @Override
    public List<PlaceResult> searchPlace(String query, String region, int pageNum, int pageSize) {
        try {
            Document document = Jsoup.connect(SEARCH_URL)
                    .data("output", "xml")
                    .data("ak", ak)
                    .data("q", encode(query))
                    .data("region", encode(region))
                    .data("page_num", pageNum + "")
                    .data("page_size", pageSize + "")
                    .get();

            Elements elements = document.select("results result");
            return getPlaceResultFromXml(elements);
        } catch (java.io.IOException e) {
            throw new IOException(e);
        }
    }

    @Override
    public List<PlaceResult> searchPlace(String query, double x1, double y1, double x2, double y2, int pageNum, int pageSize) {
        try {
            Document document = Jsoup.connect(SEARCH_URL)
                    .data("output", "xml")
                    .data("ak", ak)
                    .data("q", encode(query))
                    .data("bounds", x1 + "," + y1 + "," + x2 + "," + y2)
                    .data("page_num", pageNum + "")
                    .data("page_size", pageSize + "")
                    .get();

            Elements elements = document.select("results result");
            return getPlaceResultFromXml(elements);
        } catch (java.io.IOException e) {
            throw new IOException(e);
        }
    }

    @Override
    public List<PlaceResult> searchPlace(String query, double x, double y, int radius, int pageNum, int pageSize) {
        try {
            Document document = Jsoup.connect(SEARCH_URL)
                    .data("output", "xml")
                    .data("ak", ak)
                    .data("q", encode(query))
                    .data("location", x + "," + y)
                    .data("radius", radius + "")
                    .data("page_num", pageNum + "")
                    .data("page_size", pageSize + "")
                    .get();

            Elements elements = document.select("results result");
            return getPlaceResultFromXml(elements);
        } catch (java.io.IOException e) {
            throw new IOException(e);
        }
    }

    @Override
    public List<PlaceSuggestion> searchPlaceSuggestion(String query, String region) {
        try {
            Document document = Jsoup.connect(SEARCH_SUGGESTION_URL)
                    .data("output", "xml")
                    .data("ak", ak)
                    .data("q", encode(query))
                    .data("region", encode(region))
                    .get();

            List<PlaceSuggestion> list = new LinkedList<>();
            Iterator<Element> names = document.select("result name").iterator();
            Iterator<Element> locations = document.select("result location").iterator();
            Iterator<Element> citys = document.select("result city").iterator();
            Iterator<Element> districts = document.select("result district").iterator();

            while (names.hasNext()) {
                String name = names.next().text();
                String city = citys.next().text();
                String district = districts.next().text();
                Element location = locations.next();
                Double x = Double.parseDouble(location.select("lat").text());
                Double y = Double.parseDouble(location.select("lng").text());
                PlaceSuggestion ps = new PlaceSuggestion(name, x, y, city, district);
                list.add(ps);
            }

            return list;
        } catch (java.io.IOException e) {
            throw new IOException(e);
        }
    }

    @Override
    public Location ip2Location(String ip) {
        try {
            Document document = Jsoup.connect(SEARCH_SUGGESTION_URL)
                    .data("ak", ak)
                    .data("ip", ip)
                    .data("coor", "bd09ll")
                    .get();

            String content = document.toString();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(content);

            String address = root.path("content").path("address").asText();
            String city = root.path("content").path("address_detail").path("city").asText();
            Integer cityCode = root.path("content").path("address_detail").path("city_code").asInt();
            String district = root.path("content").path("address_detail").path("district").asText("未知");
            String province = root.path("content").path("address_detail").path("province").asText("未知");
            String street = root.path("content").path("address_detail").path("street").asText("未知");
            String streetNumber = root.path("content").path("address_detail").path("street_number").asText("未知");
            Double x = root.path("content").path("point").path("x").asDouble();
            Double y = root.path("content").path("point").path("y").asDouble();
            return new Location(address, city, cityCode, district, province, street, streetNumber, x, y);
        } catch (java.io.IOException e) {
            throw new IOException(e);
        }
    }

    /* ---------------------------------------------------------------------------------------------- */

    private List<PlaceResult> getPlaceResultFromXml(Elements elements) {
        List<PlaceResult> list = new LinkedList<>();

        for (Element el : elements) {
            String name = el.select("name").text();
            Double x = Double.parseDouble(el.select("location lat").text());
            Double y = Double.parseDouble(el.select("location lng").text());
            String address = el.select("address").text();
            String telephone = el.select("telephone").text();
            PlaceResult placeResult = new PlaceResult(name, x, y, address, telephone);
            LOGGER.debug("{}", placeResult);
            list.add(placeResult);
        }
        return list;
    }

    private String encode(String s) {
        return s;
    }
}
