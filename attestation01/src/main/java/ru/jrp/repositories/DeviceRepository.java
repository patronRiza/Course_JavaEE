package ru.jrp.repositories;

import ru.jrp.entity.Devices;

import java.util.List;

public interface DeviceRepository {
    int save(Devices devices);
    public List<Devices> getAll();
    int update(Devices devices, Boolean status, Long id);
    int delete(Long id);
}
