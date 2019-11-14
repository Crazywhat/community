package com.jockey.community.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.jockey.community.mapper2"
        ,sqlSessionFactoryRef = "sqlSessionFactoryH2"
        ,sqlSessionTemplateRef = "sqlSessionTemplateH2")
public class MybatisH2DatasourceConfig {

    @Qualifier("h2DataSource")
    @Autowired
    private DataSource dataSource;


    @Bean
    SqlSessionFactory sqlSessionFactoryH2() {
        SqlSessionFactory sessionFactory = null;
        try {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(dataSource);
            sessionFactory = bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }


    @Bean
    SqlSessionTemplate sqlSessionTemplateH2() {
        return new SqlSessionTemplate(sqlSessionFactoryH2());
    }

}
