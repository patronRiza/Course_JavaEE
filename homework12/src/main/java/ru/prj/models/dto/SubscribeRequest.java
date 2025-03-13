package ru.prj.models.dto;

import java.util.List;

public record SubscribeRequest(Long id, List<String> courses) {

}
