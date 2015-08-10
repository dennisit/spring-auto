package com.github.yingzhuo.baidulbs;

import com.github.yingzhuo.baidulbs.result.LocationInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Optional;

public final class BaiduLbsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaiduLbsService.class);

    final private RestTemplate restTemplate;

    private String ak;

    public BaiduLbsService() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
    }

    @SuppressWarnings("unchecked")
    public Optional<LocationInformation> findLocationByIpAddress(String ipAddress) {
        try {
            final String urlTemplate = "http://api.map.baidu.com/location/ip?ip=%s&ak=%s&coor=bd09ll";
            String url = String.format(urlTemplate, ipAddress, ak);

            Map<String, Object> result = restTemplate.getForObject(url, Map.class);
            int code = (Integer) result.get("status");
            if (0 != code) {
                return Optional.empty();
            }
            Map<String, Object> point = (Map<String, Object>) ((Map<String, Object>) result.get("content")).get("point");
            double x = Double.parseDouble((String) point.get("x"));
            double y = Double.parseDouble((String) point.get("y"));
            Map<String, Object> details = (Map<String, Object>) ((Map<String, Object>) result.get("content")).get("address_detail");

            LocationInformation info = new LocationInformation();
            info.setProvince(details.get("province").toString());
            info.setCity(details.get("city").toString());
            info.setCityCode(details.get("city_code").toString());
            info.setX(x);
            info.setY(y);

            return Optional.of(info);
        } catch (RestClientException e) {
            return Optional.empty();
        } catch (NumberFormatException e) {
            return Optional.empty();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }
}
