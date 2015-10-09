package com.github.yingzhuo.spring.auto.baidulbs.core;


import com.github.yingzhuo.spring.auto.baidulbs.domain.PlaceResult;
import com.github.yingzhuo.spring.auto.baidulbs.domain.PlaceSuggestion;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SimpleBaiduLbsService implements BaiduLbsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleBaiduLbsService.class);
    private static final String SEARCH_URL = "http://api.map.baidu.com/place/v2/search";
    private static final String SEARCH_SUGGESTION_URL = "http://api.map.baidu.com/place/v2/suggestion";

    private final String ak;

    public SimpleBaiduLbsService(String ak) {
        this.ak = ak;
    }

    public static void main(String[] args) {
        BaiduLbsService service = new SimpleBaiduLbsService("n8GMgEtsqd59TQwuAbSvFMbm");
        service.searchPlaceSuggestion("天安门", "北京");
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
