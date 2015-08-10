package test;


import com.github.yingzhuo.baidulbs.BaiduLbsService;
import com.github.yingzhuo.baidulbs.result.LocationInformation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
public class TestCases {


    @Autowired
    private BaiduLbsService service;

    @Test
    public void test() {
        Optional<LocationInformation> information = service.findLocationByIpAddress("202.198.16.3");
        System.out.println("-------------------------------");
        information.ifPresent(System.out::println);
        System.out.println("-------------------------------");
    }

}
