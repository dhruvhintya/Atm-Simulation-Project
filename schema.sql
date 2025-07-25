CREATE DATABASE atm_simulation;
USE atm_simulation;

CREATE TABLE users (
    account_number VARCHAR(20) PRIMARY KEY,
    pin VARCHAR(10) NOT NULL,
    balance DECIMAL(10,2) NOT NULL
);

INSERT INTO users (account_number, pin, balance) VALUES
('1234567890', '1783', 10000.00),
('22223333', '3814', 5000.00);

SELECT * FROM atm_simulation; 