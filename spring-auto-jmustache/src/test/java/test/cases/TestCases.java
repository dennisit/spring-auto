package test.cases;

import com.github.yingzhuo.spring.auto.jmustache.JmustacheTemplate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.TestApplication;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
public class TestCases {

    @Resource
    private JmustacheTemplate template;

    @Test
    public void test() {
        String s = template.render("hello", "这美丽的世界");
        Assert.assertEquals(s, "你好,这美丽的世界.");
    }
}
