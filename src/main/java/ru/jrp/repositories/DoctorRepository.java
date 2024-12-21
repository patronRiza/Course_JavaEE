package ru.jrp.repositories;

import ru.jrp.entity.Doctor;

import java.util.List;

public interface DoctorRepository {
    int save(Doctor doctor);
    List<Doctor> getAll();
    int update(Doctor doctor);
    int delete(Long id);
}
