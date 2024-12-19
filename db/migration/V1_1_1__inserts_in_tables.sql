insert into Patients (patient_id, full_name, address)
values (1, 'Иван Иванов', 'Москва, ул. Ленина, 12'),
       (2, 'Петр Петров', 'Санкт-Петербург, Невский проспект, 45'),
       (3, 'Анна Сидорова', 'Казань, ул. Баумана, 3'),
       (4, 'Елена Кузнецова', 'Новосибирск, ул. Кирова, 78'),
       (5, 'Дмитрий Смирнов', 'Екатеринбург, ул. Мира, 15');

insert into Doctors (doctor_id, full_name, profession)
values (1, 'Александр Смирнов', 'Кардиолог'),
       (2, 'Екатерина Иванова', 'Хирург'),
       (3, 'Михаил Петров', 'Терапевт'),
       (4, 'Ольга Николаева', 'Дерматолог'),
       (5, 'Сергей Козлов', 'Невролог');

insert into Medical_cards (id, patient_id, description)
values (1, 1, 'Пациент с гипертонией, необходим регулярный контроль давления.'),
       (2, 2, 'Пациент с переломом руки, прошел курс лечения.'),
       (3, 3, 'Пациент с аллергией на амoxicillin, рекомендуется исключить из лечения.'),
       (4, 4, 'Пациент с диагнозом псориаз, проходит курс дерматологического лечения.'),
       (5, 5, 'Пациент с мигренью, рекомендуется прием обезболивающих препаратов.');

insert into Records (id, patient_id, doctor_id, date_of_admission)
values (6,1, 3, '2023-10-01'),
       (7, 2, 2, '2023-10-02'),
       (8, 3, 4, '2023-10-03'),
       (9, 4, 5, '2023-10-04'),
       (10, 5, 1, '2023-10-05');