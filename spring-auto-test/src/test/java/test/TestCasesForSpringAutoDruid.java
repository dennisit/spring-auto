package test;

import com.github.yingzhuo.spring.auto.Boot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@SuppressWarnings("all")
public class TestCasesForSpringAutoDruid {

    @Autowired
    private DataSource ds;

    @Test
    public void test1() {
        System.out.println(ds == null);
    }

}
