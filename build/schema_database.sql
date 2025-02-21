CREATE DATABASE IF NOT EXISTS rubrica;
USE rubrica;

CREATE TABLE IF NOT EXISTS persone (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    indirizzo VARCHAR(100),
    telefono VARCHAR(20) UNIQUE NOT NULL,
    eta INT CHECK (eta >= 0 AND eta <= 120)
);

CREATE TABLE IF NOT EXISTS utenti (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO utenti (username, password) VALUES ('admin', 'password123');