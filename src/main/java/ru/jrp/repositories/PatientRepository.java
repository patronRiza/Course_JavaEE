package ru.jrp.repositories;

import ru.jrp.entity.Patient;

import java.util.List;

public interface PatientRepository {
    int save(Patient patient);
    public List<Patient> getAll();
    int update(Patient patient);
    int delete(Long id);
}
