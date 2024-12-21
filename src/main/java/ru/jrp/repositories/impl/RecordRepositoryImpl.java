package ru.jrp.repositories.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.jrp.config.JDBCTemplateConfig;
import ru.jrp.entity.Record;
import ru.jrp.repositories.RecordRepository;

import java.util.List;

public class RecordRepositoryImpl implements RecordRepository {

    private final JdbcTemplate jdbcTemplate = JDBCTemplateConfig.jdbcTemplate();

    public static final String SAVE_RECORD= "insert into records values(?,?,?,?)";
    public static final String FIND_ALL = "select * from records";
    public static final String UPDATE_RECORD = "update records set patient_id=?, doctor_id=?, date_of_admission=? where id=?";
    public static final String DELETE_RECORD = "delete from records where id=?";

    @Override
    public int save(Record record) {
        return jdbcTemplate.update(SAVE_RECORD, record.getId(), record.getPatient_id(), record.getDoctor_id(), record.getDate_of_admission());
    }

    @Override
    public List<Record> getAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Record.class));
    }

    @Override
    public int update(Record record) {
        return jdbcTemplate.update(UPDATE_RECORD, record.getPatient_id(), record.getDoctor_id(), record.getDate_of_admission(), record.getId());
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update(DELETE_RECORD, id);
    }
}
