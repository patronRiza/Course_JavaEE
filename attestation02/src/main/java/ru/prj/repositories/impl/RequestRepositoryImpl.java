package ru.prj.repositories.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.prj.config.JDBCTemplateConfig;
import ru.prj.entity.Request;
import ru.prj.repositories.RequestRepository;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RequestRepositoryImpl implements RequestRepository {

    private final JdbcTemplate jdbcTemplate = JDBCTemplateConfig.jdbcTemplate();

    @Override
    public void save(Request request) {
        jdbcTemplate.update(SAVE_REQUEST, toParams(request));
    }

    @Override
    public Request findById(Long id) {
        return getAll().stream()
                .filter(request -> request.getRequestId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No request found with id: " + id));
    }

    @Override
    public List<Request> getAll() {
        return jdbcTemplate.query(FIND_ALL, requestRowMapper);
    }

    @Override
    public void update(Long requestId, Long userId, String string, LocalDateTime timestamp) {
        jdbcTemplate.update(UPDATE_REQUEST, string, userId, timestamp, requestId);
    }

    @Override
    public void deleteById(Long id) {
        int result = jdbcTemplate.update(DELETE_REQUEST, id);

        if (result == 0) throw new IllegalArgumentException("No request found with id: " + id);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(DELETE_ALL);
    }

    /*@Override
    public Map<String, Long> getTypeCounts() {
        try (Stream<Request> requestStream = jdbcTemplate.queryForStream(FIND_ALL, requestRowMapper)) {
            return requestStream
                    .collect(Collectors.groupingBy(Request::getDeviceType, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Map.of("0", 0L);
        }
    }*/

    @Override
    public Map<String, Long> getTypeCounts() {
        try (Stream<Request> requestStream = getAll().stream()) {
            return requestStream
                    .collect(Collectors.groupingBy(Request::getDeviceType, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Map.of("0", 0L);
        }
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

    private Object[] toParams(Request request) {
        return new Object[]{
                request.getUserId(),
                request.getDeviceType(),
                request.getIssueDescription(),
                request.getStatus(),
                request.getAssigneeId(),
                request.getDateOfCreation(),
                request.getDateOfUpdate()
        };
    }
}
