package ru.jrp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private Long id;
    private Long patient_id;
    private Long doctor_id;
    private Date date_of_admission;

    @Override
    public String toString() {
        return "\nRecord{" +
                "id=" + id +
                ", patient_id=" + patient_id +
                ", doctor_id=" + doctor_id +
                ", date=" + date_of_admission +
                '}';
    }
}
