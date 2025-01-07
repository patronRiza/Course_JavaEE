package ru.jrp.config;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JDBCTemplateConfig {

    public static JdbcTemplate jdbcTemplate() {
        var driver = new DriverManagerDataSource("jdbc:postgresql://localhost:5433/attestation", "postgres", "root");
        driver.setDriverClassName("org.postgresql.Driver");
        driver.setSchema("public");
        return new JdbcTemplate(driver);
    }
}
