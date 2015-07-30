package com.github.yingzhuo.spring.auto.mybatis;

import org.apache.ibatis.session.ExecutorType;
import org.mybatis.spring.MyBatisExceptionTranslator;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;

@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties(ConfigBean.class)
@ConditionalOnBean(DataSource.class)
@ConditionalOnClass({SqlSessionTemplate.class, SqlSessionFactoryBean.class})
public class MyBatisConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisConfiguration.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ConfigBean configBean;

    /* -------------------------------------------------------------------------------------- */

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Throwable {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfigLocation(configBean.getConfigLocation());
        bean.setFailFast(configBean.isFailFast());
        return bean;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Throwable {

        LOGGER.debug("create SqlSession's instance.");

        SqlSessionTemplate bean;

        if (configBean.isUseExceptionTranslator()) {
            bean =
                    new SqlSessionTemplate(
                            sqlSessionFactory().getObject(),
                            ExecutorType.valueOf(configBean.getExecutorType()), new MyBatisExceptionTranslator(dataSource, true)
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
