USE tp1;

-- DB Cleanup
DELETE FROM Libro;
DELETE FROM FichaBibliografica;
ALTER TABLE Libro AUTO_INCREMENT = 1;
ALTER TABLE FichaBibliografica AUTO_INCREMENT = 1;

-- Creacion de TEMPORARY TABLES 
DROP TEMPORARY TABLE IF EXISTS pool_autores;
CREATE TEMPORARY TABLE pool_autores (nombre VARCHAR(150));
INSERT INTO pool_autores (nombre) VALUES
('Gabriel García Márquez'), ('Julio Cortázar'), ('Ernesto Sábato'),
('Jorge Luis Borges'), ('Isabel Allende'), ('Adolfo Bioy Casares'),
('Roberto Bolaño'), ('Mario Vargas Llosa'), ('José Saramago'),
('Miguel de Cervantes'), ('William Shakespeare'), ('Jane Austen'),
('Mary Shelley'), ('Bram Stoker'), ('Ernest Hemingway'),
('George Orwell'), ('Franz Kafka'), ('Fiódor Dostoyevski'),
('León Tolstói'), ('Stephen King'), ('Dan Brown'),
('Umberto Eco'), ('Antoine de Saint-Exupéry'), ('Oscar Wilde'),
('Herman Melville'), ('James Joyce'), ('Marcel Proust');

DROP TEMPORARY TABLE IF EXISTS pool_titulos;
CREATE TEMPORARY TABLE pool_titulos (titulo VARCHAR(150));
INSERT INTO pool_titulos (titulo) VALUES
('El silencio de las estrellas'), ('Sombras del pasado'),
('El eco del tiempo'), ('La última carta'), ('Cielos de invierno'),
('Las puertas del destino'), ('Luz en la penumbra'), ('Sueños de papel'),
('Los días dorados'), ('Ecos de la memoria'), ('Las voces del mar'),
('Bajo el mismo cielo'), ('El guardián del secreto'), ('Los caminos del alma'),
('La casa del río'), ('Más allá del horizonte'), ('El fin de la inocencia'),
('Tiempo de cenizas'), ('Corazones en guerra'), ('El espejo del alma');

DROP TEMPORARY TABLE IF EXISTS pool_editores;
CREATE TEMPORARY TABLE pool_editores (nombre VARCHAR(150));
INSERT INTO pool_editores (nombre) VALUES
('Alfaguara'), ('Anagrama'), ('Sudamericana'), ('Emecé'),
('Plaza & Janés'), ('Seix Barral'), ('Penguin Classics'),
('Ballantine Books'), ('Taurus'), ('Debolsillo'), ('Planeta'),
('Siglo XXI Editores'), ('Tusquets'), ('Editorial Losada'),
('Editorial Paidos'), ('Viking Press'), ('Charles Scribner’s Sons'),
('Bompiani'), ('Lumen'), ('HarperCollins');

DROP TEMPORARY TABLE IF EXISTS pool_idiomas;
CREATE TEMPORARY TABLE pool_idiomas (idioma VARCHAR(30));
INSERT INTO pool_idiomas (idioma) VALUES
('Español'), ('Inglés'), ('Francés'), ('Alemán'), ('Italiano'), ('Portugués');


INSERT INTO Libro (titulo, autor, editor, fechaPublicacion)
SELECT 
  CONCAT(
    (SELECT titulo FROM pool_titulos ORDER BY RAND() LIMIT 1),
    ' ',
    FLOOR(RAND() * 9999)
  ),
  (SELECT nombre FROM pool_autores ORDER BY RAND() LIMIT 1),    
  (SELECT nombre FROM pool_editores ORDER BY RAND() LIMIT 1),
  DATE_ADD('1950-01-01', INTERVAL seq.n DAY)
FROM (
    SELECT @row := @row + 1 AS n
    FROM (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4
          UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) t1,
         (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4
          UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) t2,
         (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4
          UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) t3,
         (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4
          UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) t4,
         (SELECT @row := 0) r
    LIMIT 10000
) seq;

-- Insertar 10.000 Fichas
INSERT INTO FichaBibliografica (isbn, idioma, estanteria, libro_id)
SELECT
  CONCAT('000-', LPAD(l.id, 9, '0')),
  (SELECT idioma FROM pool_idiomas ORDER BY RAND() LIMIT 1),
  FLOOR(1 + RAND() * 100),
  l.id
FROM Libro l;