package test;

import com.github.yingzhuo.spring.auto.Boot;
import com.github.yingzhuo.spring.auto.domain.User;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@SuppressWarnings("all")
public class TestCasesForSpringAutoMybatis {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Test
    public void test1() {
        List<User> list = sqlSessionTemplate.selectList("user.findAll");
        list.forEach(System.out::println);
        Assert.assertEquals(2, list.size());
    }

}
