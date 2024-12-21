package ru.jrp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    private Long doctor_id;
    private String full_name;
    private String profession;

    @Override
    public String toString() {
        return "\nDoctor{" +
                "doctor_id=" + doctor_id +
                ", name='" + full_name + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }
}
