package ru.prj.repositories.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.prj.config.JDBCTemplateConfig;
import ru.prj.models.Order;
import ru.prj.repositories.IOrderRepo;

import java.util.List;

@Repository
public class OrderRepo implements IOrderRepo {

    private final JdbcTemplate jdbcTemplate = JDBCTemplateConfig.jdbcTemplate();

    @Override
    public List<Order> getAll() {
        return jdbcTemplate.query(FIND_ALL, orderRowMapper);
    }

    public Integer getCountOfOrders() {
        return getAll().size();
    }

    public Double getAverage() {
        return getAll().stream()
                .mapToDouble(Order::price)
                .sum() / getCountOfOrders();
    }

    private static final RowMapper<Order> orderRowMapper = (rs, rowNum) ->
        new Order(
                rs.getLong("id"),
                rs.getString("article_of_product"),
                rs.getInt("count_of_product"),
                rs.getDouble("price"),
                rs.getTimestamp("dateOfOrder").toLocalDateTime()
    );
}
