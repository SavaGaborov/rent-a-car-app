CREATE SCHEMA IF NOT EXISTS rentacar;

CREATE TABLE users
(
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    created_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted BOOLEAN NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(128) UNIQUE NOT NULL,
    password VARCHAR(200) NOT NULL,
    role VARCHAR(25) NOT NULL,
    phone_number VARCHAR(50),
    reset_password_code VARCHAR(8),
    reset_password_code_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

