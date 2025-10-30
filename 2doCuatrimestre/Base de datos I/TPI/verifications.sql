USE tp1;

-- Total de registros:
SELECT 
    (SELECT COUNT(*) FROM Libro) AS total_libros,
    (SELECT COUNT(*) FROM FichaBibliografica) AS total_fichas;


-- Cardinalidad del Dominio:
-- Libros sin ficha
SELECT COUNT(*) AS libros_fichas_null
FROM Libro l
LEFT JOIN FichaBibliografica f ON l.id = f.libro_id
WHERE f.id IS NULL;

-- Fichas sin libro
SELECT COUNT(*) AS fichas_libros_null
FROM FichaBibliografica f
LEFT JOIN Libro l ON f.libro_id = l.id
WHERE l.id IS NULL;


-- Rango de valores del Dominio:
-- Fecha de publicacion: 
SELECT
    MIN(fechaPublicacion) AS min_fecha,
    MAX(fechaPublicacion) AS max_fecha
FROM Libro;

-- Estanteria: 
SELECT
    MIN(estanteria) AS min_estanteria,
    MAX(estanteria) AS max_estanteria
FROM FichaBibliografica;