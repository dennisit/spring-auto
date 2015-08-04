package test.cases;

import com.github.yingzhuo.spring.auto.chufasms.ChufaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.TestApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
public class TestCases {

    @Autowired
    private ChufaService chufaService;

    @Test
    public void test() {
        chufaService.send("18916944373", "您的验证码是11111【呼啦】");
    }
}
