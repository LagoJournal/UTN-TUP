CREATE OR REPLACE VIEW vista_stock_general AS
SELECT 
    l.id,
    l.titulo,
    l.autor,
    l.editor,
    YEAR(l.fechaPublicacion) as añoPublicacion,
    f.isbn,
    f.idioma,
    f.estanteria
FROM Libro l
LEFT JOIN FichaBibliografica f ON l.id = f.libro_id
WHERE l.eliminado = FALSE;

CREATE OR REPLACE VIEW vista_stock_publica AS
SELECT
    l.id,
    l.titulo,
    l.autor,
    l.editor,
    YEAR(l.fechaPublicacion) AS añoPublicacion,
    f.idioma
FROM Libro l
LEFT JOIN FichaBibliografica f ON l.id = f.libro_id
WHERE l.eliminado = FALSE;

CREATE OR REPLACE VIEW vista_resumen_publica AS
SELECT
    l.id,
    l.titulo,
    l.autor,
    YEAR(l.fechaPublicacion) AS añoPublicacion,
    COALESCE(f.idioma, 'Desconocido') AS idioma
FROM Libro l
LEFT JOIN FichaBibliografica f ON l.id = f.libro_id
WHERE l.eliminado = FALSE;