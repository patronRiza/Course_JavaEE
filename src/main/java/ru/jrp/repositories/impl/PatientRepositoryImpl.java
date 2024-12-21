package ru.jrp.repositories.impl;

import org.springframework.jdbc.core.*;
import ru.jrp.config.JDBCTemplateConfig;
import ru.jrp.entity.Patient;
import ru.jrp.repositories.PatientRepository;

import java.util.List;

public class PatientRepositoryImpl implements PatientRepository {

    private final JdbcTemplate jdbcTemplate = JDBCTemplateConfig.jdbcTemplate();

    public static final String SAVE_PATIENT= "insert into patients values(?,?,?)";
    public static final String FIND_ALL = "select * from patients";
    public static final String UPDATE_PATIENT = "update patients set full_name=?, address=?  where patient_id=?";
    public static final String DELETE_PATIENT = "delete from patients where patient_id=?";

    @Override
    public int save(Patient patient) {
        return jdbcTemplate.update(SAVE_PATIENT, patient.getPatient_id(), patient.getFull_name(), patient.getAddress());
    }

    @Override
    public List<Patient> getAll() {
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Patient.class));
    }

    @Override
    public int update(Patient patient) {
        return jdbcTemplate.update(UPDATE_PATIENT, patient.getFull_name(), patient.getAddress(), patient.getPatient_id());
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update(DELETE_PATIENT, id);
    }

    private static final RowMapper<Patient> patientRowMapper = (rs, rowNum) -> {
        Long id = rs.getLong("patient_id");
        String fullName = rs.getString("full_name");
        String address = rs.getString("address");

        return new Patient(id, fullName, address);
    };
}
