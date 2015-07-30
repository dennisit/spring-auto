package test;

import com.github.yingzhuo.spring.auto.Boot;
import com.github.yingzhuo.spring.auto.jmustache.ConfigBean;
import com.github.yingzhuo.spring.auto.jmustache.JmustacheTemplate;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@SuppressWarnings("all")
public class TestCasesForSpringAutoJmustache {

    @Autowired
    private JmustacheTemplate template;

    @Autowired
    private ConfigBean configBean;

    @Test
    public void test1() {
        String result = template.render("test", "世界");
        Assert.assertEquals("你好 世界", result);
    }

    @Test
    public void test2() {
        System.out.println(configBean.getEncoding());
        System.out.println(configBean.getPrefix());
        System.out.println(configBean.getSuffix());
    }
}
