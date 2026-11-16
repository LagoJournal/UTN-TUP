-- =========================================================
-- Actividad 2 - Base de datos Aerolinea
-- =========================================================

-- b) Creamos la base y las tablas en orden de dependencia
DROP DATABASE IF EXISTS aerolinea;
CREATE DATABASE aerolinea CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE aerolinea;

-- Tablas sin FK
CREATE TABLE horarios (
    id_horario INT         NOT NULL,
    hora_vuelo VARCHAR(20) NOT NULL,
    PRIMARY KEY (id_horario)
) ENGINE=InnoDB;

CREATE TABLE avion (
    codigo INT         NOT NULL,
    marca  VARCHAR(50) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    PRIMARY KEY (codigo)
) ENGINE=InnoDB;

CREATE TABLE clientes (
    codigo           INT         NOT NULL,
    nombre           VARCHAR(50) NOT NULL,
    apellido         VARCHAR(50) NOT NULL,
    fecha_nacimiento DATE        NOT NULL,
    tipo             VARCHAR(20) NOT NULL,
    usuario          VARCHAR(20) NOT NULL,
    PRIMARY KEY (codigo)
) ENGINE=InnoDB;

-- Destinos depende de horarios
CREATE TABLE destinos (
    id_destino INT         NOT NULL,
    id_horario INT         NOT NULL,
    nombre     VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_destino),
    CONSTRAINT fk_destino_horario FOREIGN KEY (id_horario)
        REFERENCES horarios (id_horario)
        ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

-- Vuelos depende de avion y destinos
CREATE TABLE vuelos (
    id_vuelo      INT         NOT NULL,
    disponibilidad VARCHAR(20) NOT NULL,
    tipo_vuelo    VARCHAR(20) NOT NULL,
    codigo_avion  INT         NOT NULL,
    id_destino    INT         NOT NULL,
    PRIMARY KEY (id_vuelo),
    CONSTRAINT fk_vuelo_avion FOREIGN KEY (codigo_avion)
        REFERENCES avion (codigo)
        ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_vuelo_destino FOREIGN KEY (id_destino)
        REFERENCES destinos (id_destino)
        ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

-- Reservas depende de vuelos y clientes
CREATE TABLE reservas (
    id_reserva     INT         NOT NULL,
    estado         VARCHAR(20) NOT NULL,
    id_vuelo       INT         NOT NULL,
    codigo_cliente INT         NOT NULL,
    PRIMARY KEY (id_reserva),
    CONSTRAINT fk_reserva_vuelo FOREIGN KEY (id_vuelo)
        REFERENCES vuelos (id_vuelo)
        ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_reserva_cliente FOREIGN KEY (codigo_cliente)
        REFERENCES clientes (codigo)
        ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

-- d) Insertamos datos: horarios, avion, clientes -> destinos -> vuelos -> reservas
INSERT INTO horarios (id_horario, hora_vuelo) VALUES
    (1, '06:30'),
    (2, '12:15'),
    (3, '18:45');

INSERT INTO avion (codigo, marca, estado) VALUES
    (101, 'Boeing',  'Operativo'),
    (102, 'Airbus',  'Operativo'),
    (103, 'Embraer', 'Mantenimiento');

INSERT INTO clientes (codigo, nombre, apellido, fecha_nacimiento, tipo, usuario) VALUES
    (1, 'Lucia',   'Fernandez', '1990-03-14', 'Frecuente', 'lfernandez'),
    (2, 'Martin',  'Sosa',      '1985-11-02', 'Estandar',  'msosa'),
    (3, 'Carla',   'Benitez',   '1998-07-21', 'Premium',   'cbenitez'),
    (4, 'Diego',   'Acosta',    '1979-01-30', 'Estandar',  'dacosta'),
    (5, 'Agustin', 'Lago',      '1995-05-10', 'Frecuente', 'alago');

INSERT INTO destinos (id_destino, id_horario, nombre) VALUES
    (1, 1, 'Bariloche'),
    (2, 2, 'Mendoza'),
    (3, 3, 'Salta');

INSERT INTO vuelos (id_vuelo, disponibilidad, tipo_vuelo, codigo_avion, id_destino) VALUES
    (1001, 'Disponible', 'Nacional', 101, 1),
    (1002, 'Completo',   'Nacional', 102, 1),
    (1003, 'Disponible', 'Nacional', 102, 2),
    (1004, 'Disponible', 'Nacional', 101, 3);

INSERT INTO reservas (id_reserva, estado, id_vuelo, codigo_cliente) VALUES
    (1, 'Confirmada', 1001, 1),
    (2, 'Confirmada', 1001, 5),
    (3, 'Pendiente',  1002, 2),
    (4, 'Cancelada',  1003, 3),
    (5, 'Confirmada', 1003, 4),
    (6, 'Confirmada', 1004, 5),
    (7, 'Pendiente',  1002, 3),
    (8, 'Confirmada', 1004, 1);

-- e) Visualizamos el DDL y los indices de cada tabla
SHOW CREATE TABLE horarios;
SHOW CREATE TABLE avion;
SHOW CREATE TABLE clientes;
SHOW CREATE TABLE destinos;
SHOW CREATE TABLE vuelos;
SHOW CREATE TABLE reservas;

SHOW INDEX FROM horarios;
SHOW INDEX FROM avion;
SHOW INDEX FROM clientes;
SHOW INDEX FROM destinos;
SHOW INDEX FROM vuelos;
SHOW INDEX FROM reservas;

-- f) Plan de ejecucion: vuelos y clientes de un destino
EXPLAIN
SELECT v.id_vuelo, v.tipo_vuelo, c.codigo, c.apellido, c.nombre
FROM destinos d
INNER JOIN vuelos v   ON v.id_destino = d.id_destino
INNER JOIN reservas r ON r.id_vuelo = v.id_vuelo
INNER JOIN clientes c ON c.codigo = r.codigo_cliente
WHERE d.nombre = 'Bariloche';

-- g) Clientes ordenados por apellido y nombre: plan sin indice
SELECT codigo, apellido, nombre FROM clientes ORDER BY apellido, nombre;
EXPLAIN SELECT codigo, apellido, nombre FROM clientes ORDER BY apellido, nombre;

-- Agregamos un indice compuesto y repetimos el plan
CREATE INDEX idx_cliente_apellido_nombre ON clientes (apellido, nombre);
EXPLAIN SELECT codigo, apellido, nombre FROM clientes ORDER BY apellido, nombre;

-- h) Vista ordenada por apellido y nombre
CREATE OR REPLACE VIEW v_clientes_ordenados AS
SELECT codigo, apellido, nombre
FROM clientes
ORDER BY apellido, nombre;

EXPLAIN SELECT * FROM v_clientes_ordenados;
-- Consultamos la vista ordenando solo por nombre
EXPLAIN SELECT * FROM v_clientes_ordenados ORDER BY nombre;
SELECT * FROM v_clientes_ordenados ORDER BY nombre;
