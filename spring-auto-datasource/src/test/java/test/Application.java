package test;

import com.github.yingzhuo.spring.auto.datasource.composite.tool.DataSourceConfigBean;
import com.github.yingzhuo.spring.auto.datasource.composite.tool.DataSourceCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.Iterator;

@SpringBootApplication
@EnableConfigurationProperties(DataSourceConfigBean.class)
public class Application {

    @Autowired
    private DataSourceConfigBean dataSourceConfigBean;

    @PostConstruct
    public void init() {
        System.out.println(dataSourceConfigBean.getMasterDataSourceCore());

        Iterator<DataSourceCore> it = dataSourceConfigBean.getSlaveDataSourceCoreIterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
