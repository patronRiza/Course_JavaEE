package ru.prj.entity;

import java.time.LocalDateTime;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Request {
    private Long requestId;
    private Long userId;
    private String deviceType;
    private String issueDescription;
    private String status;
    private Long assigneeId;  //ID сотрудника выполняющего заявку
    private LocalDateTime dateOfCreation;
    private LocalDateTime dateOfUpdate;

    public Request(Long userId, String deviceType, String issueDescription) {
        this.userId = userId;
        this.deviceType = deviceType;
        this.issueDescription = issueDescription;
    }
}
