package ru.jrp.repositories;

import ru.jrp.entity.Record;

import java.util.List;

public interface RecordRepository {
    int save(Record record);
    public List<Record> getAll();
    int update(Record record);
    int delete(Long id);
}
