package ru.prj.repositories.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.prj.config.JDBCTemplateConfig;
import ru.prj.entity.User;
import ru.prj.repositories.UserRepository;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate = JDBCTemplateConfig.jdbcTemplate();

    @Override
    public void create(User user) {
        jdbcTemplate.update(SAVE_USER, toParams(user));
    }

    @Override
    public User findByEmail(String email) {
        return getAll().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No device found with email: " + email));
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(FIND_ALL, userRowMapper);
    }

    @Override
    public void update(Long id, String name, String email, String password, String phoneNumber) {
        jdbcTemplate.update(UPDATE_USER, name, email, password, phoneNumber, id);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(DELETE_USER, id);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(DELETE_ALL);
    }

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String phone_number = rs.getString("phone_number");
        String role = rs.getString("role");

        return new User(id, name, email, password, phone_number, role);
    };

    private Object[] toParams(User user) {
        return new Object[]{
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getRole()
        };
    }
}
