package ru.jrp.entity;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Devices {
    private Long deviceId;
    private Long userId;
    private String deviceType;
    private String deviceModel;
    private String serialNumber;
    private Boolean warrantyStatus;
    private LocalDateTime createTime;

    public Devices(Long userId, String deviceType, String deviceModel, String serialNumber) {
        this.userId = userId;
        this.deviceType = deviceType;
        this.deviceModel = deviceModel;
        this.serialNumber = serialNumber;
    }
}
