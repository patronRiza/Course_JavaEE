package ru.prj.repositories;

import ru.prj.entity.User;

import java.util.List;

public interface UserRepository {
    String SAVE_USER = """
            insert into users(name, email, password, phone_number, role)
            values(?,?,?,?,?)
            """;
    String FIND_ALL = "select * from users";
    String UPDATE_USER = "update users set name=?, email=?, password=?, phone_number=? where id=?";
    String DELETE_USER = "delete from users where id=?";
    String DELETE_ALL = "delete from users";

    void create(User user);
    User findByEmail(String email);
    List<User> getAll();
    void update(Long id, String name, String email, String password, String phoneNumber);
    void deleteById(Long id);
    void deleteAll();
}
