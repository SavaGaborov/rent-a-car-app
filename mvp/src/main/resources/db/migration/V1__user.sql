CREATE SCHEMA IF NOT EXISTS rentacar;

CREATE TABLE users
(
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    created_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted BOOLEAN NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(128),
    password VARCHAR(20),
    role VARCHAR(25),
    phone_number VARCHAR(50)
);

