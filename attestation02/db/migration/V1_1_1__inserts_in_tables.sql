INSERT INTO users (name, email, password, phone_number, role)
VALUES
    ('Иван Иванов', 'ivan@example.com', 'password123', '+79161234567', 'client'),
    ('Петр Петров', 'petr@example.com', 'password456', '+79162345678', 'client'),
    ('Анна Сидорова', 'anna@example.com', 'password789', '+79163456789', 'client'),
    ('Мария Кузнецова', 'maria@example.com', 'password101', '+79164567890', 'client'),
    ('Алексей Смирнов', 'alex@example.com', 'password112', '+79165678901', 'admin'),
    ('Елена Васильева', 'elena@example.com', 'password131', '+79166789012', 'client'),
    ('Дмитрий Павлов', 'dmitry@example.com', 'password415', '+79167890123', 'client'),
    ('Ольга Новикова', 'olga@example.com', 'password161', '+79168901234', 'client'),
    ('Сергей Федоров', 'sergey@example.com', 'password718', '+79169012345', 'client'),
    ('Татьяна Морозова', 'tatyana@example.com', 'password919', '+79160123456', 'client');

INSERT INTO devices (user_id, device_type, device_model, serial_number, warranty_status, created_at)
VALUES
    (1, 'Ноутбук', 'Lenovo ThinkPad X1', 'SN123456', TRUE, '2023-09-01 10:00:00'),
    (2, 'Смартфон', 'iPhone 14 Pro', 'SN654321', FALSE, '2023-09-02 11:00:00'),
    (3, 'Принтер', 'HP LaserJet Pro', 'SN789012', TRUE, '2023-09-03 12:00:00'),
    (4, 'Планшет', 'Samsung Galaxy Tab S8', 'SN345678', FALSE, '2023-09-04 13:00:00'),
    (5, 'Монитор', 'Dell UltraSharp U2723QE', 'SN901234', TRUE, '2023-09-05 14:00:00'),
    (6, 'Ноутбук', 'MacBook Pro 16', 'SN567890', FALSE, '2023-09-06 15:00:00'),
    (7, 'Смартфон', 'Google Pixel 7', 'SN234567', TRUE, '2023-09-07 16:00:00'),
    (8, 'Принтер', 'Canon PIXMA G6020', 'SN890123', FALSE, '2023-09-08 17:00:00'),
    (9, 'Планшет', 'iPad Pro 12.9', 'SN456789', TRUE, '2023-09-09 18:00:00'),
    (10, 'Монитор', 'LG UltraFine 27UN880', 'SN012345', FALSE, '2023-09-10 19:00:00');

INSERT INTO requests (user_id, device_type, issue_description, status, assigned_to, created_at, updated_at)
VALUES
    (1, 'Ноутбук', 'Не включается', 'new', 5, '2023-10-01 10:00:00', '2023-10-01 10:00:00'),
    (2, 'Смартфон', 'Разбит экран', 'in_progress', 5, '2023-10-02 11:00:00', '2023-10-02 11:00:00'),
    (3, 'Принтер', 'Не печатает', 'new', NULL, '2023-10-03 12:00:00', '2023-10-03 12:00:00'),
    (4, 'Планшет', 'Не заряжается', 'completed', 5, '2023-10-04 13:00:00', '2023-10-04 14:00:00'),
    (5, 'Монитор', 'Нет изображения', 'new', NULL, '2023-10-05 14:00:00', '2023-10-05 14:00:00'),
    (6, 'Ноутбук', 'Перегревается', 'in_progress', 5, '2023-10-06 15:00:00', '2023-10-06 15:00:00'),
    (7, 'Смартфон', 'Не работает микрофон', 'new', NULL, '2023-10-07 16:00:00', '2023-10-07 16:00:00'),
    (8, 'Принтер', 'Зажевывает бумагу', 'completed', 5, '2023-10-08 17:00:00', '2023-10-08 18:00:00'),
    (9, 'Планшет', 'Не включается', 'new', NULL, '2023-10-09 18:00:00', '2023-10-09 18:00:00'),
    (10, 'Планшет', 'Мерцает экран', 'in_progress', 5, '2023-10-10 19:00:00', '2023-10-10 19:00:00');