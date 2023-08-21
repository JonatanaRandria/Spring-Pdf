package com.example.prog4.config.db;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = "com.example.prog4.repository.employeeRepository",
        entityManagerFactoryRef = "employeeEntityManager",
        transactionManagerRef = "employeeTransactionManager")
@AllArgsConstructor
public class EmployeeDbConfig {
    private DbConfig dbConfig;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource employeeDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean employeeEntityManager() {
        return dbConfig.entityManagerCreator(
                employeeDataSource(),
                "com.example.prog4.repository.employeeRepository"
        );
    }

    @Bean
    public PlatformTransactionManager employeeTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(employeeEntityManager().getObject());
        return transactionManager;
    }

}

