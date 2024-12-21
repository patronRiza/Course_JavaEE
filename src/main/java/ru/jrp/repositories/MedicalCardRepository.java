package ru.jrp.repositories;

import ru.jrp.entity.Doctor;
import ru.jrp.entity.MedicalCard;

import java.util.List;

public interface MedicalCardRepository {
    int save(MedicalCard medicalCard);
    List<MedicalCard> getAll();
    int update(MedicalCard medicalCard);
    int delete(Long id);
}
