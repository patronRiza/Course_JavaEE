package ru.prj.repositories;

import ru.prj.models.Order;

import java.util.List;

public interface IOrderRepo {
    String FIND_ALL = "select * from orders";

    List<Order> getAll();
}
