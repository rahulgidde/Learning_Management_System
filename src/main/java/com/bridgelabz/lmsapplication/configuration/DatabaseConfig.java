package com.bridgelabz.lmsapplication.configuration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    @Bean
    public DataSource getDataSource()
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(System.getenv().get("spring.datasource.url"));
        dataSourceBuilder.username(System.getenv().get("spring.datasource.username"));
        dataSourceBuilder.password(System.getenv().get("spring.datasource.password"));
        return dataSourceBuilder.build();
    }
}
