package com.jockey.community.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:datasource.properties")
public class H2DataSourceConfig {

    @Bean(name = "h2DataSourceProperties")
    @Primary
    @ConfigurationProperties("app.datasource.h2")
    public DataSourceProperties h2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "h2DataSource")
    @Primary
    public HikariDataSource h2DataSource() {
        return h2DataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

}
