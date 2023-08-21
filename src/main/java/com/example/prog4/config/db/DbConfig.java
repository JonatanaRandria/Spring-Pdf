package com.example.prog4.config.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DbConfig {
    private final String hbm2ddl;
    private final String dialect;

    public DbConfig(@Value("${hibernate-hbm2ddl-auto}") String hbm2ddl, @Value("${hibernate-dialect}") String dialect){
        this.hbm2ddl = hbm2ddl;
        this.dialect = dialect;
    }

    public LocalContainerEntityManagerFactoryBean entityManagerCreator(DataSource dataSource, String packageToScan){
        LocalContainerEntityManagerFactoryBean em =  new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(packageToScan);

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter((vendorAdapter));

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", this.hbm2ddl);
        properties.put("hibernate.dialect", this.dialect);
        em.setJpaPropertyMap(properties);
        return em;
    }
}
