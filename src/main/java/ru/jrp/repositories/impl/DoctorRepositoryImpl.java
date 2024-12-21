package ru.jrp.repositories.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.jrp.config.JDBCTemplateConfig;
import ru.jrp.entity.Doctor;
import ru.jrp.repositories.DoctorRepository;

import java.util.List;

public class DoctorRepositoryImpl implements DoctorRepository {

    private final JdbcTemplate jdbcTemplate = JDBCTemplateConfig.jdbcTemplate();

    public static final String SAVE_DOCTOR = "insert into doctors values(?,?,?)";
    public static final String FIND_ALL = "select * from doctors";
    public static final String UPDATE_DOCTOR = "update doctors set full_name=?, profession=?  where doctor_id=?";
    public static final String DELETE_DOCTOR = "delete from doctors where doctor_id=?";

    @Override
    public int save(Doctor doctor) {
        return jdbcTemplate.update(SAVE_DOCTOR, doctor.getDoctor_id(), doctor.getFull_name(), doctor.getProfession());
    }

    @Override
    public List<Doctor> getAll() {
        return jdbcTemplate.query(FIND_ALL, doctorRowMapper);
    }

    @Override
    public int update(Doctor doctor) {
        return jdbcTemplate.update(UPDATE_DOCTOR, doctor.getFull_name(), doctor.getProfession(), doctor.getDoctor_id());
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update(DELETE_DOCTOR, id);
    }

    private final RowMapper<Doctor> doctorRowMapper = (rs, rowNum) -> {
        Long id = rs.getLong("doctor_id");
        String fullName = rs.getString("full_name");
        String profession = rs.getString("profession");

        return new Doctor(id, fullName, profession);
    };
}
