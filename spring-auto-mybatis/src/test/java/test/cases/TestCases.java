package test.cases;

import domain.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.TestApplication;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
public class TestCases {

    @Autowired
    private SqlSession sqlSession;

    @Test
    public void test() {
        List<User> list = sqlSession.selectList("findAll");
        list.forEach(System.out::println);
        Assert.assertEquals(2, list.size());
    }

}
