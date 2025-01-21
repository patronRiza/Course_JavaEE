DELETE
FROM requests
WHERE status = 'completed';
-----------------------------------

UPDATE users
SET phone_number = '+79161112233'
WHERE id = 2;

UPDATE requests
SET
    status = 'completed',
    updated_at = NOW()
WHERE
    id = 1;
------------------------------------

SELECT r.id   AS request_id,
       u.name AS user_name,
       d.device_type,
       d.device_model,
       r.issue_description,
       r.status,
       r.created_at
FROM requests r
         JOIN
     users u ON r.user_id = u.id
         JOIN
     devices d ON r.device_type = d.device_type AND r.user_id = d.user_id
WHERE r.assigned_to = 5;

SELECT u.name AS user_name,
       u.email,
       d.device_type,
       d.device_model,
       d.serial_number
FROM users u
         JOIN
     devices d ON u.id = d.user_id;