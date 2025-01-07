package ru.jrp.repositories.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.jrp.config.JDBCTemplateConfig;
import ru.jrp.entity.User;
import ru.jrp.repositories.UserRepository;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate = JDBCTemplateConfig.jdbcTemplate();

    public static final String SAVE_USER = "insert into users(email, password, phone_number) values(?,?,?)";
    public static final String FIND_ALL = "select * from users";
    public static final String UPDATE_USER = "update users set name=?, email=?, password=?, phone_number=? where id=?";
    public static final String DELETE_USER = "delete from users where id=?";

    @Override
    public int create(User user) {
        return jdbcTemplate.update(SAVE_USER, user.getEmail(), user.getPassword(), user.getPhoneNumber());
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(FIND_ALL, userRowMapper);
    }

    @Override
    public int update(User user, Long id) {
        return jdbcTemplate.update(UPDATE_USER, user.getName(), user.getEmail(), user.getPassword(), user.getPhoneNumber(), id);
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update(DELETE_USER, id);
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
}
