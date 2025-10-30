CREATE DATABASE IF NOT EXISTS tp1;

USE tp1;

-- Tabla Libro
CREATE TABLE IF NOT EXISTS Libro(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(150) NOT NULL,
    editor VARCHAR(150) NOT NULL,
    fechaPublicacion DATE NOT NULL,
    eliminado BOOLEAN DEFAULT FALSE,
--  CHECK Controls para evitar campos vacios
    CHECK (titulo <> ''),
    CHECK (autor <> ''),
    CHECK (editor <> '')
);

-- Tabla FichaBibliografica
CREATE TABLE IF NOT EXISTS FichaBibliografica(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR(20) NOT NULL,
    idioma VARCHAR(30) NOT NULL,
    estanteria INT,
    eliminado BOOLEAN DEFAULT FALSE,
    libro_id BIGINT UNIQUE,
--  Relacion de FK y ON DELETE CASCADE
CONSTRAINT fk_ficha_libro FOREIGN KEY (libro_id) REFERENCES Libro(id) ON DELETE CASCADE,
--  CHECK Controls para evitar campos vacios
    CHECK (isbn <> ''),
    CHECK (idioma <> '')
);