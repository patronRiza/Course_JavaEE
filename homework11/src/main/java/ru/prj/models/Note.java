package ru.prj.models;

import java.time.LocalDateTime;

public record Note(Long id, LocalDateTime dateTime, String title, String content) {}
