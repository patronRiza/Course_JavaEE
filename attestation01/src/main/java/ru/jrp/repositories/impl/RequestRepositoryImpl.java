package ru.jrp.repositories.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.jrp.config.JDBCTemplateConfig;
import ru.jrp.entity.Request;
import ru.jrp.repositories.RequestRepository;

import java.time.LocalDateTime;
import java.util.List;

public class RequestRepositoryImpl implements RequestRepository {

    private final JdbcTemplate jdbcTemplate = JDBCTemplateConfig.jdbcTemplate();

    public static final String SAVE_REQUEST = "insert into requests(user_id, device_type, issue_description) values(?,?,?)";
    public static final String FIND_ALL = "select * from requests";
    public static final String UPDATE_REQUEST = "update requests set status=?, assigned_to=? where id=?";
    public static final String DELETE_REQUEST = "delete from requests where id=?";

    @Override
    public int save(Request request) {
        return jdbcTemplate.update(SAVE_REQUEST, request.getUserId(), request.getDeviceType(), request.getIssueDescription());
    }

    @Override
    public List<Request> getAll() {
        return jdbcTemplate.query(FIND_ALL, requestRowMapper);
    }

    @Override
    public int update(String string, Long userId, Long requestId) {
        return jdbcTemplate.update(UPDATE_REQUEST, string, userId, requestId);
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update(DELETE_REQUEST, id);
    }

    private static final RowMapper<Request> requestRowMapper = (rs, rowNum) -> {
        Long id = rs.getLong("id");
        Long user_id = rs.getLong("user_id");
        String device_type = rs.getString("device_type");
        String issue_description = rs.getString("issue_description");
        String status = rs.getString("status");
        Long assigned_to = rs.getLong("assigned_to");
        LocalDateTime created_at = rs.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updated_at = rs.getTimestamp("updated_at").toLocalDateTime();

        return new Request(id, user_id, device_type, issue_description, status, assigned_to, created_at, updated_at);
    };
}
