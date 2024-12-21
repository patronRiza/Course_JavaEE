package ru.jrp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private Long patient_id;
    private String full_name;
    private String address;

    @Override
    public String toString() {
        return "\nPatient{" +
                "patient_id=" + patient_id +
                ", name='" + full_name + '\'' +
                ", address='" + address + '\'' +
                "}";
    }
}
