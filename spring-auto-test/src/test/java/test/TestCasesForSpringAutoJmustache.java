package test;

import com.github.yingzhuo.spring.auto.jmustache.JmustacheTemplate;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Boot.class)
//@SuppressWarnings("all")
public class TestCasesForSpringAutoJmustache {

    @Autowired
    private JmustacheTemplate template;

    @Test
    public void test1() {
        String result = template.render("test", "世界");
        Assert.assertEquals("你好 世界", result);
    }

}
