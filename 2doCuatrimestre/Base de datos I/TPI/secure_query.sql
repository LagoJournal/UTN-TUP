USE tp1;


DROP PROCEDURE IF EXISTS sp_buscar_libro_seguro;
DELIMITER //
CREATE PROCEDURE sp_buscar_libro_seguro(
    IN p_titulo VARCHAR(150),    
    IN p_autor  VARCHAR(150),    
    IN p_isbn   VARCHAR(30),     
    IN p_limit  INT              
)
BEGIN
  IF p_limit IS NULL OR p_limit <= 0 OR p_limit > 1000 THEN
    SET p_limit = 100;
  END IF;

  SELECT 
    l.id,
    l.titulo,
    l.autor,
    l.editor,
    YEAR(l.fechaPublicacion) AS anioPublicacion,
    f.idioma
  FROM Libro l
  LEFT JOIN FichaBibliografica f ON f.libro_id = l.id
  WHERE l.eliminado = FALSE
    AND (p_isbn IS NULL OR f.isbn = p_isbn)                           
    AND (p_titulo IS NULL OR l.titulo LIKE CONCAT('%', p_titulo, '%')) 
    AND (p_autor  IS NULL OR l.autor  LIKE CONCAT('%', p_autor, '%'))  
  ORDER BY l.fechaPublicacion DESC
  LIMIT p_limit;
END //
DELIMITER ;



-- 1
CALL sp_buscar_libro_seguro('Corazones', NULL, NULL, 10);

-- 2
CALL sp_buscar_libro_seguro(NULL, NULL, '000-000000003', 10);

-- 3
CALL sp_buscar_libro_seguro("'; DROP TABLE Libro; --", NULL, NULL, 10);

-- 4
CALL sp_buscar_libro_seguro("anything' OR '1'='1", NULL, NULL, 10);

-- 5
CALL sp_buscar_libro_seguro(NULL, NULL, NULL, 1000000); -- será recortado a 50 por seguridad

-- 6
SHOW TABLES LIKE 'Libro';
SELECT COUNT(*) AS total_libros FROM Libro;
