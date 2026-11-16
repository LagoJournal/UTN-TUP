-- =========================================================
-- Actividad 1 - Base de datos Libreria
-- =========================================================

-- a) Creamos la base con su cotejamiento
DROP DATABASE IF EXISTS libreria;
CREATE DATABASE libreria CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE libreria;

-- Creamos articulos (precio en DECIMAL para admitir centavos)
CREATE TABLE articulos (
    Codigo_Articulo VARCHAR(8)    NOT NULL,
    Articulo        VARCHAR(100)  NOT NULL,
    Precio          DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (Codigo_Articulo)
) ENGINE=InnoDB;

-- Creamos detalle_factura con PK compuesta; la FK a facturas se agrega luego
CREATE TABLE detalle_factura (
    Numero_Factura  INT        NOT NULL,
    Item_Factura    INT        NOT NULL,
    Codigo_Articulo VARCHAR(8) NOT NULL,
    Unidades        INT        NOT NULL,
    PRIMARY KEY (Numero_Factura, Item_Factura),
    CONSTRAINT fk_detalle_articulo FOREIGN KEY (Codigo_Articulo)
        REFERENCES articulos (Codigo_Articulo)
        ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

-- Creamos localidades y clientes
CREATE TABLE localidades (
    Codigo_Postal INT          NOT NULL,
    Localidad     VARCHAR(100) NOT NULL,
    PRIMARY KEY (Codigo_Postal)
) ENGINE=InnoDB;

CREATE TABLE clientes (
    Numero_Cliente  INT          NOT NULL,
    Apellido_Cliente VARCHAR(60) NOT NULL,
    Nombre_Cliente  VARCHAR(120) NOT NULL,
    Calle           VARCHAR(60)  NOT NULL,
    Numero_Calle    INT          NOT NULL,
    Codigo_Postal   INT          NOT NULL,
    PRIMARY KEY (Numero_Cliente),
    CONSTRAINT fk_cp FOREIGN KEY (Codigo_Postal)
        REFERENCES localidades (Codigo_Postal)
        ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

-- Creamos facturas relacionada con clientes
CREATE TABLE facturas (
    Numero_Factura INT  NOT NULL,
    Fecha          DATE NOT NULL,
    Numero_Cliente INT  NOT NULL,
    PRIMARY KEY (Numero_Factura),
    CONSTRAINT fk_factura_cliente FOREIGN KEY (Numero_Cliente)
        REFERENCES clientes (Numero_Cliente)
        ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

-- Con facturas creada, agregamos la FK pendiente de detalle_factura
ALTER TABLE detalle_factura
    ADD CONSTRAINT fk_detalle_factura FOREIGN KEY (Numero_Factura)
        REFERENCES facturas (Numero_Factura)
        ON DELETE RESTRICT ON UPDATE RESTRICT;

-- b) Cargamos los datos respetando el orden de las FK
INSERT INTO localidades (Codigo_Postal, Localidad) VALUES
    (1870, 'Avellaneda'),
    (1900, 'La Plata');

INSERT INTO clientes (Numero_Cliente, Apellido_Cliente, Nombre_Cliente, Calle, Numero_Calle, Codigo_Postal) VALUES
    (21, 'Rodriguez', 'Facundo', 'Rivero',       41, 1870),
    (22, 'Martinez',  'Natalia', 'San Eloy',     82, 1870),
    (23, 'Garcia',    'Carlos',  'Av. De Italia', 245, 1900);

INSERT INTO articulos (Codigo_Articulo, Articulo, Precio) VALUES
    ('1-0023_D', 'Auriculares inalambricos',   175),
    ('2-0023_D', 'Teclado inalambrico',        245),
    ('3-0023_D', 'Mini adaptador USB-C USB 2.0', 75),
    ('4-0023_D', 'Cable Red UTP RJ45',          90),
    ('5-0023_D', 'Camara Web USB',             165);

INSERT INTO facturas (Numero_Factura, Fecha, Numero_Cliente) VALUES
    (335, '2024-08-01', 21),
    (336, '2024-08-02', 22),
    (337, '2024-08-02', 23);

INSERT INTO detalle_factura (Numero_Factura, Item_Factura, Codigo_Articulo, Unidades) VALUES
    (335, 1, '2-0023_D', 10),
    (335, 2, '4-0023_D', 12),
    (335, 3, '5-0023_D',  1),
    (336, 1, '1-0023_D',  2),
    (336, 2, '3-0023_D',  2),
    (337, 1, '2-0023_D', 25);

-- c) Visualizamos el DDL de cada tabla y sus indices
SHOW CREATE TABLE localidades;
SHOW CREATE TABLE clientes;
SHOW CREATE TABLE articulos;
SHOW CREATE TABLE facturas;
SHOW CREATE TABLE detalle_factura;

SHOW INDEX FROM localidades;
SHOW INDEX FROM clientes;
SHOW INDEX FROM articulos;
SHOW INDEX FROM facturas;
SHOW INDEX FROM detalle_factura;

-- d) Clientes que compraron el 02/08/2024
SELECT DISTINCT c.Numero_Cliente, c.Apellido_Cliente, c.Nombre_Cliente
FROM clientes c
INNER JOIN facturas f ON f.Numero_Cliente = c.Numero_Cliente
WHERE f.Fecha = '2024-08-02';
