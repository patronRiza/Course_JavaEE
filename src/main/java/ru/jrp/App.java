package ru.jrp;

import ru.jrp.repositories.DoctorRepository;
import ru.jrp.repositories.MedicalCardRepository;
import ru.jrp.repositories.PatientRepository;
import ru.jrp.repositories.RecordRepository;
import ru.jrp.repositories.impl.DoctorRepositoryImpl;
import ru.jrp.repositories.impl.MedicalCardRepositoryImpl;
import ru.jrp.repositories.impl.PatientRepositoryImpl;
import ru.jrp.repositories.impl.RecordRepositoryImpl;

public class App {

    private static final DoctorRepository doctorRepository = new DoctorRepositoryImpl();
    private static final PatientRepository patientRepository = new PatientRepositoryImpl();
    private static final MedicalCardRepository medicalCardRepository = new MedicalCardRepositoryImpl();
    private static final RecordRepository recordRepository = new RecordRepositoryImpl();

    public static void main(String[] args) {
        //SQL requests for table Doctors
        //int saveDoctor = doctorRepository.save(new Doctor(6L, "Ризван", "Стоматолог"));
        //System.out.println(saveDoctor);
        var getAllDoctors = doctorRepository.getAll();
        System.out.println(getAllDoctors);
        //int updateDoctor = doctorRepository.update(new Doctor(6L, "Ризван", "Хирург"));
        //System.out.println(updateDoctor);
        //int deleteDoctor = doctorRepository.delete(6L);
        //SQL requests for table Patients
        //int savePatient = patientRepository.save(new Patient(6L, "Ризван", "Хади Такташа, 121"));
        //System.out.println(savePatient);
        var getAllPatients = patientRepository.getAll();
        System.out.println(getAllPatients);
        //int updatePatient = patientRepository.update(new Patient(6L, "Ризван", "Меридианная, 1"));
        //System.out.println(updatePatient);
        //int deletePatient = patientRepository.delete(6L);
        //SQL requests for table Medical_cards
        //int saveMedCard = medicalCardRepository.save(new MedicalCard(6L, 1L, "Кариес"));
        //System.out.println(saveMedCard);
        var getAllMedCards = medicalCardRepository.getAll();
        System.out.println(getAllMedCards);
        //int updateMedCard = medicalCardRepository.update(new MedicalCard(6L, 1L, "Ушиб"));
        //System.out.println(updateMedCard);
        //int deleteMedCard = medicalCardRepository.delete(6L);
        //SQL requests for table Medical_cards
        //int saveRecord = recordRepository.save(new Record(12L, 1L, 5L,new Date(1723723920000L)));
        //System.out.println(saveRecord);

        var getAllRecords = recordRepository.getAll();
        System.out.println(getAllRecords);

        //int updateRecord = recordRepository.update(new Record(11L, 1L, 4L, new Date(1723205520000L)));
        //System.out.println(updateRecord);

        //int deleteRecord = recordRepository.delete(11L);
    }
}
