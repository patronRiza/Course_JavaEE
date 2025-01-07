package ru.jrp.repositories.impl;

import org.springframework.jdbc.core.*;
import ru.jrp.config.JDBCTemplateConfig;
import ru.jrp.entity.Devices;
import ru.jrp.repositories.DeviceRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class DeviceRepositoryImpl implements DeviceRepository {

    private final JdbcTemplate jdbcTemplate = JDBCTemplateConfig.jdbcTemplate();

    public static final String SAVE_DEVICE = "insert into devices(user_id, device_type, device_model, serial_number) values(?,?,?,?)";
    public static final String FIND_ALL = "select * from devices";
    public static final String UPDATE_DEVICE = "update devices set user_id=?, warranty_status=?  where id=?";
    public static final String DELETE_DEVICE = "delete from devices where id=?";

    @Override
    public int save(Devices devices) {
        return jdbcTemplate.update(SAVE_DEVICE, devices.getUserId(), devices.getDeviceType(), devices.getDeviceModel(), devices.getSerialNumber());
    }

    @Override
    public List<Devices> getAll() {
        return jdbcTemplate.query(FIND_ALL, deviceRowMapper);
    }

    @Override
    public int update(Devices devices, Boolean status, Long id) {
        return jdbcTemplate.update(UPDATE_DEVICE, devices.getUserId(), status, id);
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update(DELETE_DEVICE, id);
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
}
