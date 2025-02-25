package ru.prj.models;

import java.time.LocalDateTime;

public record Order(Long id, String article, Integer count, Double price, LocalDateTime dateOfOrder) {}
