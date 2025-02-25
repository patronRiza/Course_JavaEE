package ru.prj.repositories;

import ru.prj.entity.Request;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface RequestRepository {
    String SAVE_REQUEST = """
            insert into requests(user_id, device_type, issue_description, status, assigned_to, created_at, updated_at)
            values(?,?,?,?,?,?,?)
            """;
    String FIND_ALL = "select * from requests";
    String UPDATE_REQUEST = "update requests set status=?, assigned_to=?, updated_at=? where id=?";
    String DELETE_REQUEST = "delete from requests where id=?";
    String DELETE_ALL = "delete from requests";

    void save(Request request);

    Request findById(Long id);

    List<Request> getAll();

    void update(Long requestId, Long userId, String string, LocalDateTime time);

    void deleteById(Long id);

    void deleteAll();

    Map<String, Long> getTypeCounts();
}
