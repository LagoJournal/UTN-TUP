USE tp1;

INSERT INTO Libro (titulo, autor, editor, fechaPublicacion, eliminado)
VALUES ('Libro PK Test', 'Autor Uno', 'Editorial Test', '2020-01-01', FALSE);


SET @id_existente := LAST_INSERT_ID();


INSERT INTO Libro (id, titulo, autor, editor, fechaPublicacion, eliminado)
VALUES (@id_existente, 'Duplicado PK', 'Autor Dos', 'Editor Dos', '2021-01-01', FALSE);



INSERT INTO FichaBibliografica (isbn, idioma, estanteria, eliminado, libro_id)
VALUES ('999-INVALID', 'Español', 10, FALSE, 999999999);


INSERT INTO FichaBibliografica (isbn, idioma, estanteria, eliminado, libro_id)
VALUES (CONCAT('978-', LPAD(FLOOR(RAND()*999999999),9,'0')), 'Español', 25, FALSE, @id_existente);

SELECT * FROM FichaBibliografica ORDER BY id DESC LIMIT 1;