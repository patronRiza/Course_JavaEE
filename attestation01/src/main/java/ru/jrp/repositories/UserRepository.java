package ru.jrp.repositories;

import ru.jrp.entity.User;

import java.util.List;

public interface UserRepository {
    int create(User user);
    List<User> getAll();
    int update(User user, Long l);
    int delete(Long id);
}
