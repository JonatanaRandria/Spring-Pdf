package com.example.prog4.config.db;

import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayMigrationConfig {
    private final DataSource employeeDataSource;
    private final DataSource cnapsDataSource;
    private final String employeeMigrationPath;
    private final String cnapsMigrationPath;

    public FlywayMigrationConfig(
            DataSource employeeDataSource,
            @Qualifier("cnapsEmployeeDataSource") DataSource cnapsDataSource,
            @Value("${spring.flyway.locations}") String employeeMigrationPath,
            @Value("${spring.flyway.cnaps-datasource.locations}") String cnapsMigrationPath
    ) {
        this.cnapsDataSource = cnapsDataSource;
        this.employeeDataSource = employeeDataSource;
        this.cnapsMigrationPath = cnapsMigrationPath;
        this.employeeMigrationPath = employeeMigrationPath;
    }

    @PostConstruct
    public void migrate() {
        flywayConfig(this.employeeDataSource, this.employeeMigrationPath);
        flywayConfig(this.cnapsDataSource, this.cnapsMigrationPath);
    }

    private void flywayConfig(DataSource dataSource, String location) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations(location)
                .schemas("public")
                .target(MigrationVersion.LATEST)
                .validateMigrationNaming(true)
                .load();
        flyway.migrate();
    }
}
