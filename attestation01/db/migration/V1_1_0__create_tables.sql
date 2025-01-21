CREATE TABLE IF NOT EXISTS users
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(255),
    email        VARCHAR(255) UNIQUE NOT NULL,
    password     VARCHAR(255)        NOT NULL,
    phone_number VARCHAR(20),
    role         VARCHAR(50) DEFAULT 'client'
);

CREATE TABLE IF NOT EXISTS requests
(
    id                SERIAL PRIMARY KEY,
    user_id           BIGINT       NOT NULL,
    device_type       VARCHAR(100) NOT NULL,
    issue_description TEXT         NOT NULL,
    status            VARCHAR(50) DEFAULT 'new',
    assigned_to       BIGINT,
    created_at        TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    updated_at        TIMESTAMP   DEFAULT NOW(),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (assigned_to) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS devices
(
    id              SERIAL PRIMARY KEY,
    user_id         BIGINT       NOT NULL,
    device_type     VARCHAR(100) NOT NULL,
    device_model    VARCHAR(255) NOT NULL,
    serial_number   VARCHAR(255) UNIQUE,
    warranty_status BOOLEAN   DEFAULT FALSE,
    created_at      TIMESTAMP DEFAULT NOW(),
    FOREIGN KEY (user_id) REFERENCES users (id)
);
