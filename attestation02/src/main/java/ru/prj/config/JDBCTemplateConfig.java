package ru.prj.config;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JDBCTemplateConfig {

    public static JdbcTemplate jdbcTemplate() {
        // Для сборки исполняемого файла в Docker
        //var driver = new DriverManagerDataSource("jdbc:postgresql://postgres:5432/attestation", "postgres", "root");
        // Для миграции
        var driver = new DriverManagerDataSource("jdbc:postgresql://localhost:5433/attestation", "postgres", "root");
        driver.setDriverClassName("org.postgresql.Driver");
        driver.setSchema("public");
        return new JdbcTemplate(driver);
    }
}
