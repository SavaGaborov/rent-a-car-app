CREATE SCHEMA IF NOT EXISTS rentacar;

CREATE TABLE IF NOT EXISTS users
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

CREATE TABLE IF NOT EXISTS cars
(
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    created_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted BOOLEAN NOT NULL,
    brand VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    type VARCHAR(15) NOT NULL,
    registration_number VARCHAR(20) NOT NULL,
    year_built INT NOT NULL,
    available BOOLEAN NOT NULL,
    times_borrowed INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS rents
(
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    created_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted BOOLEAN NOT NULL,
    car_id INT NOT NULL,
    borrower_id INT NOT NULL,
    staff_id INT NOT NULL,
    rent_status VARCHAR(25) NOT NULL,
    price DECIMAL,
    FOREIGN KEY (car_id) REFERENCES cars(id),
    FOREIGN KEY (borrower_id) REFERENCES users(id),
    FOREIGN KEY (staff_id) REFERENCES users(id)
);
