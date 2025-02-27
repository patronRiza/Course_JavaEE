package ru.prj.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@Profile("production")
public class DockerJDBCTemplateConfig implements IJDBCTemplateConfig {

    private final String datasourceUrl;
    private final String datasourceUsername;
    private final String datasourcePassword;
    private final String driverClassName;

    public JdbcTemplate jdbcTemplate() {

        var driver = new DriverManagerDataSource(datasourceUrl, datasourceUsername, datasourcePassword);
        driver.setDriverClassName(driverClassName);
        return new JdbcTemplate(driver);
    }

    public DockerJDBCTemplateConfig(@Value("${spring.datasource.url}") final String url,
                                    @Value("${spring.datasource.username}") final String username,
                                    @Value("${spring.datasource.password}") final String password,
                                    @Value("${spring.datasource.driver-class-name}") final String driver) {
        this.datasourceUrl = url;
        this.datasourceUsername = username;
        this.datasourcePassword = password;
        this.driverClassName = driver;
    }
}
