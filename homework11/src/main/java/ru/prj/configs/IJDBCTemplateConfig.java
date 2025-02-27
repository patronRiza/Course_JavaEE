package ru.prj.configs;

import org.springframework.jdbc.core.JdbcTemplate;

public interface IJDBCTemplateConfig {
    JdbcTemplate jdbcTemplate();
}
