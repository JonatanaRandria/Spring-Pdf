package com.example.prog4.config.db;

import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayMigrationConfig {
    private final DataSource employeeDataSource;
    private final DataSource cnapsDataSource;

    public FlywayMigrationConfig(DataSource employeeDataSource, @Qualifier("cnapsEmployeeDataSource") DataSource cnapsDataSource){
        this.cnapsDataSource = cnapsDataSource;
        this.employeeDataSource = employeeDataSource;
    }

    @PostConstruct
    public void migrate(){
        flywayConfig(this.employeeDataSource, "classpath:/db/migration/employee");
        flywayConfig(this.cnapsDataSource, "classpath:/db/migration/cnaps");
    }

    private void flywayConfig(DataSource dataSource, String location){
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations(location)
                .load();
        flyway.migrate();
    }
}
