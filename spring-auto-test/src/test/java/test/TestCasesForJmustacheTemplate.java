package test;

import com.github.yingzhuo.spring.auto.Boot;
import com.github.yingzhuo.spring.auto.jmustache.JmustacheTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@SuppressWarnings("all")
public class TestCasesForJmustacheTemplate {


    @Autowired
    private JmustacheTemplate jmustacheTemplate;

    @Test
    public void test1() {
        jmustacheTemplate.render("test", "世界");
    }
}
