package test;

import com.github.yingzhuo.spring.auto.Boot;
import com.github.yingzhuo.spring.auto.qiniuyun.QiniuyunService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@SuppressWarnings("all")
public class TestCasesForSpringAutoQiniuyun {

    @Autowired
    private QiniuyunService qiniuyunService;

    @Test
    public void test1() throws IOException {

//        byte[] data = IOUtils.toByteArray(new FileInputStream(new File("/Users/yingzhuo/Downloads/1.jpeg")));
//        String url = qiniuyunService.upload(data, "test/fuckme", true);
//        System.out.println(url);
    }

}
