create table Patients
(
    "patient_id" bigint primary key not null,
    "full_name" varchar(50),
    "address" varchar(50)
);

create table Doctors
(
    "doctor_id" bigint primary key not null,
    "full_name" varchar(50),
    "profession" varchar(50)
);

create table Medical_cards
(
    "id" bigint,
    "patient_id" bigint,
    foreign key (patient_id) references Patients(patient_id),
    "description" varchar(255)
);

create table Records
(
    "id" bigint primary key not null,
    "patient_id" bigint,
    foreign key (patient_id) references Patients(patient_id),
    "doctor_id" bigint,
    foreign key (doctor_id) references Doctors(doctor_id),
    unique (patient_id, doctor_id),
    "date_of_admission" date
);
