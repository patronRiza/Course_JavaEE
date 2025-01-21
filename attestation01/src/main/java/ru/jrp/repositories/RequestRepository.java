package ru.jrp.repositories;

import ru.jrp.entity.Request;

import java.util.List;

public interface RequestRepository {
    int save(Request request);
    public List<Request> getAll();
    int update(String string, Long userId, Long requestId);
    int delete(Long id);
}
