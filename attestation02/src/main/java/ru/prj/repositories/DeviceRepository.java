package ru.prj.repositories;

import ru.prj.entity.Devices;

import java.util.List;

public interface DeviceRepository {
    String SAVE_DEVICE = """
            insert into devices(user_id, device_type, device_model, serial_number, warranty_status, created_at)
            values(?,?,?,?,?,?)
            """;
    String FIND_ALL = "select * from devices";
    String UPDATE_DEVICE = "update devices set user_id=?, warranty_status=?  where id=?";
    String DELETE_DEVICE = "delete from devices where id=?";
    String DELETE_ALL = "delete from devices";

    void save(Devices devices);

    Devices findById(Long id);

    List<Devices> getAll();

    void update(Long deviceId, Long userId, Boolean warrantyStatus);

    void deleteById(Long id);

    void deleteAll();
}
