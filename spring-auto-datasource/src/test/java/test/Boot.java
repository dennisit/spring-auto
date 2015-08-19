package test;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.yingzhuo.spring.auto.datasource.CompositeDataSourceConfigSupport;
import com.github.yingzhuo.spring.auto.datasource.DataSourceSwitchAdviceSupport;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
public class Boot {

    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }

    @Bean
    public CompositeDataSourceConfigSupport compositeDataSourceConfigSupport() {
        return new CompositeDataSourceConfigSupport() {
            @Override
            public DataSource getMaster() {
                DruidDataSource bean = new DruidDataSource();
                bean.setUrl("jdbc:mysql://localhost:3306/test");
                bean.setUsername("root");
                bean.setPassword("root");
                return bean;
            }

            @Override
            public List<DataSource> getSlaves() {
                return null;
            }

            @Override
            public String getInitMethodName() {
                return "init";
            }

            @Override
            public String getDestoryMethodName() {
                return "close";
            }
        };
    }

    @Bean
    public Object dataSourceSwitchSupport() {
        return new DataSourceSwitchAdviceSupport() {
            @Override
            public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
                return super.around(joinPoint);
            }
        };
    }
}
