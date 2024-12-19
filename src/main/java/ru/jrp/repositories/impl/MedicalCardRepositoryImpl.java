package ru.jrp.repositories.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.jrp.config.JDBCTemplateConfig;
import ru.jrp.entity.MedicalCard;
import ru.jrp.repositories.MedicalCardRepository;

import java.util.List;

public class MedicalCardRepositoryImpl implements MedicalCardRepository {

    private final JdbcTemplate jdbcTemplate = JDBCTemplateConfig.jdbcTemplate();

    public static final String SAVE_MEDCARD = "insert into medical_cards values(?,?,?)";
    public static final String FIND_ALL = "select * from medical_cards";
    public static final String UPDATE_MEDCARD = "update medical_cards set patient_id=?, description=?  where id=?";
    public static final String DELETE_MEDCARD = "delete from medical_cards where id=?";

    @Override
    public int save(MedicalCard medicalCard) {
        return jdbcTemplate.update(SAVE_MEDCARD, medicalCard.getId(), medicalCard.getPatient_id(), medicalCard.getDescription());
    }

    @Override
    public List<MedicalCard> getAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(MedicalCard.class));
    }

    @Override
    public int update(MedicalCard medicalCard) {
        return jdbcTemplate.update(UPDATE_MEDCARD, medicalCard.getPatient_id(), medicalCard.getDescription(), medicalCard.getId());
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update(DELETE_MEDCARD, id);
    }
}
