package ru.jrp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalCard {
    private Long id;
    private Long patient_id;
    private String description;

    @Override
    public String toString() {
        return "\nMedicalCard{" +
                "id=" + id +
                ", patient_id=" + patient_id +
                ", description='" + description + '\'' +
                '}';
    }
}
