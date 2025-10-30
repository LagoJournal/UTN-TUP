SELECT 
    l.id,
    l.titulo,
    l.autor,
    f.isbn,
    f.idioma,
    f.estanteria
FROM Libro l
INNER JOIN FichaBibliografica f ON l.id = f.libro_id
WHERE l.eliminado = FALSE AND f.eliminado = FALSE;


SELECT 
    a.autor,
    COUNT(a.id) AS n_libros,
    (
        SELECT GROUP_CONCAT(sub.titulo ORDER BY sub.titulo SEPARATOR ', ')
        FROM (
            SELECT l2.titulo
            FROM Libro AS l2
            WHERE l2.autor = a.autor
            ORDER BY RAND()
            LIMIT 3
        ) AS sub
    ) AS ejemplos
FROM Libro AS a
WHERE a.eliminado = FALSE
GROUP BY a.autor
ORDER BY n_libros DESC
LIMIT 10;

SELECT 
    idioma,
    COUNT(*) AS total_fichas
FROM FichaBibliografica
WHERE eliminado = FALSE
GROUP BY idioma
HAVING COUNT(*) > 1000
ORDER BY total_fichas DESC;

SELECT 
    l.titulo,
    f.estanteria
FROM Libro l
JOIN FichaBibliografica f ON l.id = f.libro_id
WHERE f.estanteria > (
    SELECT AVG(estanteria) FROM FichaBibliografica
)
ORDER BY f.estanteria DESC;