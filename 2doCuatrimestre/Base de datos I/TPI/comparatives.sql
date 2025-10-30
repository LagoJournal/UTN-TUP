USE tp1;


DROP TEMPORARY TABLE IF EXISTS resultados_medicion;
CREATE TEMPORARY TABLE resultados_medicion (
    consulta VARCHAR(30),
    escenario VARCHAR(10),
    ejecucion INT,
    duracion_ms DOUBLE
);


DROP PROCEDURE IF EXISTS medir_consulta;
DELIMITER //
CREATE PROCEDURE medir_consulta(
    IN nombre_consulta VARCHAR(30),
    IN escenario VARCHAR(10),
    IN query_text TEXT
)
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 3 DO
        SET @t1 = NOW(6);
        SET @sql = query_text;
        PREPARE stmt FROM @sql;
        EXECUTE stmt;
        DEALLOCATE PREPARE stmt;
        SET @t2 = NOW(6);

        INSERT INTO resultados_medicion
        VALUES (
            nombre_consulta,
            escenario,
            i,
            ROUND(TIMESTAMPDIFF(MICROSECOND,@t1,@t2)/1000,3)
        );
        SET i = i + 1;
    END WHILE;
END //
DELIMITER ;


SELECT IF(EXISTS(
  SELECT 1 FROM INFORMATION_SCHEMA.STATISTICS
  WHERE TABLE_SCHEMA=DATABASE()
  AND TABLE_NAME='FichaBibliografica' AND INDEX_NAME='idx_isbn'),
 'ALTER TABLE FichaBibliografica DROP INDEX idx_isbn;',
 'SELECT "idx_isbn inexistente";') INTO @sql;
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SELECT IF(EXISTS(
  SELECT 1 FROM INFORMATION_SCHEMA.STATISTICS
  WHERE TABLE_SCHEMA=DATABASE()
  AND TABLE_NAME='FichaBibliografica' AND INDEX_NAME='idx_libro_id'),
 'ALTER TABLE FichaBibliografica DROP INDEX idx_libro_id;',
 'SELECT "idx_libro_id inexistente";') INTO @sql;
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SELECT IF(EXISTS(
  SELECT 1 FROM INFORMATION_SCHEMA.STATISTICS
  WHERE TABLE_SCHEMA=DATABASE()
  AND TABLE_NAME='Libro' AND INDEX_NAME='idx_fecha_publicacion'),
 'ALTER TABLE Libro DROP INDEX idx_fecha_publicacion;',
 'SELECT "idx_fecha_publicacion inexistente";') INTO @sql;
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;


CALL medir_consulta('Q1_Igualdad','SIN',
"SELECT * FROM vista_stock_general WHERE isbn = '978-000000001'");
CALL medir_consulta('Q2_Rango','SIN',
"SELECT * FROM vista_stock_general WHERE añoPublicacion BETWEEN 1990 AND 2000");
CALL medir_consulta('Q3_Join','SIN',
"SELECT * FROM vista_stock_general WHERE idioma = 'Español'");


CREATE INDEX idx_isbn ON FichaBibliografica(isbn);
CREATE INDEX idx_fecha_publicacion ON Libro(fechaPublicacion);
CREATE INDEX idx_libro_id ON FichaBibliografica(libro_id);


CALL medir_consulta('Q1_Igualdad','CON',
"SELECT * FROM vista_stock_general WHERE isbn = '978-000000001'");
CALL medir_consulta('Q2_Rango','CON',
"SELECT * FROM vista_stock_general WHERE añoPublicacion BETWEEN 1990 AND 2000");
CALL medir_consulta('Q3_Join','CON',
"SELECT * FROM vista_stock_general WHERE idioma = 'Español'");



-- Crear tabla temporal con promedios y medianas
DROP TEMPORARY TABLE IF EXISTS estadisticas;
CREATE TEMPORARY TABLE estadisticas AS
SELECT 
    consulta,
    escenario,
    ROUND(AVG(duracion_ms),3) AS promedio_ms,
    ROUND(
        CASE
            WHEN COUNT(*) % 2 = 1 THEN
                SUBSTRING_INDEX(
                    SUBSTRING_INDEX(GROUP_CONCAT(duracion_ms ORDER BY duracion_ms),
                                    ',', (COUNT(*) DIV 2)+1), ',', -1)
            ELSE
                (CAST(SUBSTRING_INDEX(
                    SUBSTRING_INDEX(GROUP_CONCAT(duracion_ms ORDER BY duracion_ms),
                                    ',', COUNT(*)/2), ',', -1) AS DECIMAL(10,3))
                 + CAST(SUBSTRING_INDEX(
                    SUBSTRING_INDEX(GROUP_CONCAT(duracion_ms ORDER BY duracion_ms),
                                    ',', (COUNT(*)/2)+1), ',', -1) AS DECIMAL(10,3))
                )/2
        END,3
    ) AS mediana_ms
FROM resultados_medicion
GROUP BY consulta, escenario
ORDER BY consulta, escenario;

SELECT * FROM estadisticas;

DROP TEMPORARY TABLE IF EXISTS estadisticas_sin;
CREATE TEMPORARY TABLE estadisticas_sin AS
SELECT consulta, mediana_ms AS mediana_sin_idx
FROM estadisticas WHERE escenario='SIN';

DROP TEMPORARY TABLE IF EXISTS estadisticas_con;
CREATE TEMPORARY TABLE estadisticas_con AS
SELECT consulta, mediana_ms AS mediana_con_idx
FROM estadisticas WHERE escenario='CON';

SELECT 
    s.consulta,
    s.mediana_sin_idx,
    c.mediana_con_idx,
    ROUND(s.mediana_sin_idx - c.mediana_con_idx,3) AS diferencia_ms,
    ROUND(s.mediana_sin_idx / c.mediana_con_idx,2) AS factor_mejora
FROM estadisticas_sin s
JOIN estadisticas_con c USING (consulta)
ORDER BY s.consulta;


SELECT * FROM resultados_medicion ORDER BY consulta, escenario, ejecucion;