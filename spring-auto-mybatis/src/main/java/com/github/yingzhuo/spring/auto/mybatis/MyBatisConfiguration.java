package com.github.yingzhuo.spring.auto.mybatis;

import org.apache.ibatis.session.ExecutorType;
import org.mybatis.spring.MyBatisExceptionTranslator;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;

@Configurable
@EnableAutoConfiguration
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties(ConfigBean.class)
@ComponentScan("com.github.yingzhuo.spring.auto.mybatis")
public class MyBatisConfiguration {

    @Autowired(required = false)
    private DataSource dataSource;

    @Autowired
    private ConfigBean configBean;

    /* -------------------------------------------------------------------------------------- */

    @Bean
    @ConditionalOnBean(DataSource.class)
    @ConditionalOnClass(SqlSessionTemplate.class)
    public SqlSessionFactoryBean sqlSessionFactory() throws Throwable {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfigLocation(configBean.getConfigLocation());
        bean.setFailFast(configBean.isFailFast());
        return bean;
    }

    @Bean
    @ConditionalOnBean(DataSource.class)
    @ConditionalOnClass(SqlSessionTemplate.class)
    public SqlSessionTemplate sqlSessionTemplate() throws Throwable {
        SqlSessionTemplate bean;

        if (configBean.isUseExceptionTranslator()) {
            bean =
                    new SqlSessionTemplate(
                            sqlSessionFactory().getObject(),
                            ExecutorType.valueOf(configBean.getExecutorType()),
                            new MyBatisExceptionTranslator(dataSource, true)
                    );

        } else {
            bean =
                    new SqlSessionTemplate(
                            sqlSessionFactory().getObject(),
                            ExecutorType.valueOf(configBean.getExecutorType())
                    );
        }

        return bean;
    }
}
