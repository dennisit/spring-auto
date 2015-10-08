package com.github.yingzhuo.spring.auto.baidulbs.core;


import com.github.yingzhuo.spring.auto.baidulbs.domain.PlaceResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class SimpleLbsService implements BaiduLbsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleLbsService.class);
    private static final String SEARCH_URL = "http://api.map.baidu.com/place/v2/search";

    private final String ak;

    public SimpleLbsService(String ak) {
        this.ak = ak;
    }

    public static void main(String[] args) {
        BaiduLbsService service = new SimpleLbsService("n8GMgEtsqd59TQwuAbSvFMbm");
        service.searchPlace("饭店", "上海", 0, 3);
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

            Elements results = document.select("results result");
            List<PlaceResult> list = new LinkedList<>();

            for (Element el : results) {
                String name = el.select("name").text();
                Double x = Double.parseDouble(el.select("location lat").text());
                Double y = Double.parseDouble(el.select("location lng").text());
                String address = el.select("address").text();
                String telephone = el.select("telephone").text();
                PlaceResult placeResult = new PlaceResult(name, x, y, address, telephone);
                list.add(placeResult);
            }

            return list;
        } catch (java.io.IOException e) {
            throw new IOException(e);
        }
    }

    private String encode(String s) {
        return s;
    }
}
