package com.jockey.community.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.jockey.community.mapper"
        ,sqlSessionFactoryRef = "sqlSessionFactorySql"
        ,sqlSessionTemplateRef = "sqlSessionTemplateSql")
public class MybatisMysqlDatasourceConfig {

    @Qualifier("mysqlDataSource")
    @Autowired
    private DataSource dataSource;


    @Bean
    SqlSessionFactory sqlSessionFactorySql() {
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
    SqlSessionTemplate sqlSessionTemplateSql() {
        return new SqlSessionTemplate(sqlSessionFactorySql());
    }

}
