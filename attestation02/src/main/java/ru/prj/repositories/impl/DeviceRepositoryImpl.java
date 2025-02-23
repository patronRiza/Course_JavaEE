package ru.prj.repositories.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.prj.config.JDBCTemplateConfig;
import ru.prj.entity.Devices;
import ru.prj.repositories.DeviceRepository;

import java.time.LocalDateTime;
import java.util.List;

public class DeviceRepositoryImpl implements DeviceRepository {

    private final JdbcTemplate jdbcTemplate = JDBCTemplateConfig.jdbcTemplate();

    @Override
    public void save(Devices devices) {
        jdbcTemplate.update(SAVE_DEVICE, toParams(devices));
    }

    @Override
    public Devices findById(Long id) {
        return getAll().stream()
                .filter(device -> device.getDeviceId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No device found with id: " + id));
    }

    @Override
    public List<Devices> getAll() {
        return jdbcTemplate.query(FIND_ALL, deviceRowMapper);
    }

    @Override
    public void update(Long deviceId, Long userId, Boolean warrantyStatus) {
        jdbcTemplate.update(UPDATE_DEVICE, userId, warrantyStatus, deviceId);
    }

    @Override
    public void deleteById(Long id) {
        int result = jdbcTemplate.update(DELETE_DEVICE, id);
        if (result == 0) throw new IllegalArgumentException("No device found with id: " + id);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(DELETE_ALL);
    }

    private static final RowMapper<Devices> deviceRowMapper = (rs, rowNum) -> {
        Long id = rs.getLong("id");
        Long user_id = rs.getLong("user_id");
        String device_type = rs.getString("device_type");
        String device_model = rs.getString("device_model");
        String serial_number = rs.getString("serial_number");
        Boolean warranty_status = Boolean.valueOf(rs.getString("warranty_status"));
        LocalDateTime created_at = rs.getTimestamp("created_at").toLocalDateTime();

        return new Devices(id, user_id, device_type, device_model, serial_number, warranty_status, created_at);
    };

    private Object[] toParams(Devices devices) {
        return new Object[]{
                devices.getUserId(),
                devices.getDeviceType(),
                devices.getDeviceModel(),
                devices.getSerialNumber(),
                devices.getWarrantyStatus(),
                devices.getCreateTime()
        };
    }
}
