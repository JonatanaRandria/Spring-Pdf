package com.example.prog4.config.db;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = "com.example.prog4.repository.cnapsRepository",
        entityManagerFactoryRef = "cnapsEmployeeEntityManager",
        transactionManagerRef = "cnapsEmployeeTransactionManager")
@AllArgsConstructor
public class CnapsEmployeeDbConfig {
    private DbConfig dbConfig;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.cnaps-datasource")
    public DataSource cnapsEmployeeDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean cnapsEmployeeEntityManager() {
        return dbConfig.entityManagerCreator(
                cnapsEmployeeDataSource(),
                "com.example.prog4.repository.cnapsRepository"
        );
    }

    @Primary
    @Bean
    public PlatformTransactionManager cnapsEmployeeTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(cnapsEmployeeEntityManager().getObject());
        return transactionManager;
    }

}

