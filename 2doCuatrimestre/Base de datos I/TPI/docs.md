# Trabajo Practico Integrador DB I

## ETAPA 1:

- Relacion de clases seleccionadas:
  Libro -> FichaBibliografica
  1 -> 1 (unidireccional)

  Cada FichaBibliografica contendra una FK UNIQUE haciendo referencia a un Libro (1:1)

- Modelos:
  LIBRO:{
  id: BIGINT PK
  titulo: VARCHAR(150) NOT NULL
  autor: VARCHAR(150) NOT NULL
  editor: VARCHAR(150) NOT NULL
  fechaPublicacion: DATE NOT NULL
  eliminado: BOOLEAN DEFAULT FALSE
  }

  FichaBibliografica:{
  id: BIGINT PK
  isbn: VARCHAR(20) UNIQUE
  idioma: VARCHARM(30)
  estanteria: INT
  eliminado BOOLEAN DEFAULT FALSE
  libro_id: BIGINT UNIQUE FK, ON DELETE CASCADE
  }

- Creacion de DB y Modelos en /create_schema.sql

## ETAPA 2:

- Generacion y carga de datos:
  Se realizara a traves del archivo /populate.sql

  Se le pidio a un modelo de IA la generacion de datos falsos para su utilizacion al momento de
  la generacion de datos. Se seguira una integracion por medio de TEMPORARY TABLES, en la cual se
  almacenara la informacion de los autores, titulos, editores y idiomas. Al momento de la carga de
  datos, se utilizaran estos datos de manera aleatoria realizar multiples combinaciones.

  Se utilizo una insercion de 10,000 registros usando una secuencia generada con variables.

  ```
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
  ```

  Se genera una secuencia del 1 al 10.000 usando CROSS JOIN de pequeños bloques de números y la variable @row.
  Luego, con INSERT ... SELECT, se crean los libros y sus fichas vinculadas, asignando datos aleatorios
  de manera rápida y compatible con MySQL.

- Verificaciones realizadas:
  1- Total de registros: Se verifica la correcta insercion de datos en la base de datos. Se
  esperan valores totales = 10000.
  2- Cardinalidad del Dominio: Se verifica la correcta relacion entre tablas respetando el
  dominio esperado (cada FichaBibliografica contiene una FK hacia un Libro).
  3- Rango de valores del Dominio: Se verifica que los valores de los campos se encuentren dentro
  de un rango de valores realista bajo las reglas impuestas al momento de popular la base de datos.
  _ Fecha de publicacion: Datos esperados entre 1950-01-01 y fecha de hoy.
  _ Estanteria: Valores esperados entre 1 y 100.

EJECUCION DE SCRIPT SQL create_schema.sql

```
mysql> source create_schema.sql;
```

VERIFICACION DE CREACION DE DB Y Modelos

```
mysql> SHOW TABLES;
+--------------------+
| Tables_in_tp1      |
+--------------------+
| FichaBibliografica |
| Libro              |
+--------------------+

mysql> DESCRIBE Libro;
+------------------+--------------+------+-----+---------+----------------+
| Field            | Type         | Null | Key | Default | Extra          |
+------------------+--------------+------+-----+---------+----------------+
| id               | bigint       | NO   | PRI | NULL    | auto_increment |
| titulo           | varchar(150) | NO   |     | NULL    |                |
| autor            | varchar(150) | NO   |     | NULL    |                |
| editor           | varchar(150) | NO   |     | NULL    |                |
| fechaPublicacion | date         | NO   |     | NULL    |                |
| eliminado        | tinyint(1)   | YES  |     | 0       |                |
+------------------+--------------+------+-----+---------+----------------+

mysql> DESCRIBE FichaBibliografica;
+------------+-------------+------+-----+---------+----------------+
| Field      | Type        | Null | Key | Default | Extra          |
+------------+-------------+------+-----+---------+----------------+
| id         | bigint      | NO   | PRI | NULL    | auto_increment |
| isbn       | varchar(20) | NO   |     | NULL    |                |
| idioma     | varchar(30) | NO   |     | NULL    |                |
| estanteria | int         | YES  |     | NULL    |                |
| eliminado  | tinyint(1)  | YES  |     | 0       |                |
| libro_id   | bigint      | YES  | UNI | NULL    |                |
+------------+-------------+------+-----+---------+----------------+
```

EJECUCION DE SCRIPT SQL populate.sql

```
mysql> source populate.sql;
Query OK, 10000 rows affected (0.095 sec)
Records: 10000  Duplicates: 0  Warnings: 0
```

EJECUCION DE SCRIPT SQL verifications.sql

```
mysql> source verifications.sql;

+--------------+--------------+
| total_libros | total_fichas |
+--------------+--------------+
|        10000 |        10000 |
+--------------+--------------+


+--------------------+
| libros_fichas_null |
+--------------------+
|                  0 |
+--------------------+

+--------------------+
| fichas_libros_null |
+--------------------+
|                  0 |
+--------------------+


+------------+------------+
| min_fecha  | max_fecha  |
+------------+------------+
| 1950-01-02 | 1977-05-19 |
+------------+------------+

+----------------+----------------+
| min_estanteria | max_estanteria |
+----------------+----------------+
|              1 |            100 |
+----------------+----------------+
```

## ETAPA 3:

- Query #1: JOIN Libro - FichaBibliografica
  Listado de libros activos con su ficha catalogada
  ```
  +-------+-----------------------------------+---------------------------+---------------+------------+------------+
  | id    | titulo                            | autor                     | isbn          | idioma     | estanteria |
  +-------+-----------------------------------+---------------------------+---------------+------------+------------+
  |  1    | Más allá del horizonte 4573       | Mary Shelley              | 000-000000001 | Alemán     |         98 |
  |  2    | Corazones en guerra 5734          | Fiódor Dostoyevski        | 000-000000002 | Italiano   |         21 |
  |  3    | Las voces del mar 7199            | Herman Melville           | 000-000000003 | Francés    |         28 |
      ...
  |  9999 | Sueños de papel 6184              | Antoine de Saint-Exupéry  | 000-000009999 | Inglés     |         65 |
  | 10000 | Los días dorados 2085             | Miguel de Cervantes       | 000-000010000 | Italiano   |         50 |
  +-------+-----------------------------------+---------------------------+---------------+------------+------------+
  10000 rows in set (0.033 sec)
  ```
- Query #2: JOIN + Tabla auxiliar
  Listado de los 10 editores con más libros y títulos de ejemplo.
  ```
  +---------------------+----------+-------------------------------------------------------------------------------------------------+
  | autor               | n_libros | ejemplos                                                                                        |
  +---------------------+----------+-------------------------------------------------------------------------------------------------+
  | Umberto Eco         |      419 | La última carta 3153, Los caminos del alma 4452, Tiempo de cenizas 7348                         |
  | Mario Vargas Llosa  |      402 | El guardián del secreto 215, El guardián del secreto 759, La última carta 642                   |
  | Ernesto Sábato      |      398 | El silencio de las estrellas 4326, La última carta 6771, Más allá del horizonte 7963            |
  | Fiódor Dostoyevski  |      387 | El eco del tiempo 6470, Las puertas del destino 4102, Tiempo de cenizas 8800                    |
  | Ernest Hemingway    |      387 | El guardián del secreto 1068, El guardián del secreto 7341, El silencio de las estrellas 8894   |
  | Mary Shelley        |      386 | El eco del tiempo 8079, El espejo del alma 1818, Las puertas del destino 7314                   |
  | Herman Melville     |      384 | El fin de la inocencia 3265, Las puertas del destino 1945, Las voces del mar 6156               |
  | Oscar Wilde         |      384 | El eco del tiempo 3240, Las puertas del destino 5062, Los caminos del alma 9319                 |
  | George Orwell       |      382 | El guardián del secreto 7374, Las puertas del destino 8608, Luz en la penumbra 772              |
  | Isabel Allende      |      382 | Cielos de invierno 4769, Ecos de la memoria 491, Tiempo de cenizas 2719                         |
  +---------------------+----------+-------------------------------------------------------------------------------------------------+
  10 rows in set (0.099 sec)
  ```
- Query #3: GROUP BY + HAVING
  Listado de fichas por idioma y mostrar solo los idiomas mas comunes.
  ```
  +------------+--------------+
  | idioma     | total_fichas |
  +------------+--------------+
  | Italiano   |         1702 |
  | Alemán     |         1699 |
  | Español    |         1698 |
  | Inglés     |         1670 |
  | Portugués  |         1650 |
  | Francés    |         1581 |
  +------------+--------------+
  6 rows in set (0.015 sec)
  ```
- Query #4: Subconsulta
  Detectar libros ubicados en estanterías de mayor numeración.

  ```
  +-----------------------------------+------------+
  | titulo                            | estanteria |
  +-----------------------------------+------------+
  | El guardián del secreto 8168      |         51 |
  | Bajo el mismo cielo 9502          |         51 |
  | Ecos de la memoria 5363           |         51 |
  | La casa del río 7863              |         51 |
  | La casa del río 2719              |         51 |
  ...
  | El silencio de las estrellas 7894 |        100 |
  | La última carta 2346              |        100 |
  | Ecos de la memoria 4632           |        100 |
  | Los caminos del alma 2520         |        100 |
  | Los caminos del alma 9938         |        100 |
  +-----------------------------------+------------+
  4983 rows in set (0.024 sec)
  ```

- Creacion de VIEWS:
  1 - vista_stock_general: Vista que muestra los libros activos con su ficha
  asociada.

- Comparativas con/sin indices:

  - Query #1: Busqueda con igualdad de campo (isbn)

  ```
  SELECT * FROM vista_stock_general WHERE isbn = '000-000000123';
  ```

  - Query #2: Busqueda por rango (año de publicacion)

  ```
  SELECT * FROM vista_stock_general WHERE añoPublicacion BETWEEN 1950 AND 1969;
  ```

  - Query #3: Busqueda con JOIN (integrado en la vista)

  ```
  SELECT * FROM vista_stock_general WHERE idioma = 'Español';
  ```

  - Resultados:

    ```
    +-------------+-----------+-----------+-------------+
    | consulta    | escenario | ejecucion | duracion_ms |
    +-------------+-----------+-----------+-------------+
    | Q1_Igualdad | CON       |         1 |       0.186 |
    | Q1_Igualdad | CON       |         2 |       0.083 |
    | Q1_Igualdad | CON       |         3 |        0.06 |
    | Q1_Igualdad | SIN       |         1 |        3.37 |
    | Q1_Igualdad | SIN       |         2 |       2.453 |
    | Q1_Igualdad | SIN       |         3 |       2.294 |
    | Q2_Rango    | CON       |         1 |       3.375 |
    | Q2_Rango    | CON       |         2 |       2.951 |
    | Q2_Rango    | CON       |         3 |       3.005 |
    | Q2_Rango    | SIN       |         1 |       5.232 |
    | Q2_Rango    | SIN       |         2 |       3.724 |
    | Q2_Rango    | SIN       |         3 |       3.517 |
    | Q3_Join     | CON       |         1 |       3.866 |
    | Q3_Join     | CON       |         2 |      31.699 |
    | Q3_Join     | CON       |         3 |      37.306 |
    | Q3_Join     | SIN       |         1 |       4.541 |
    | Q3_Join     | SIN       |         2 |      55.945 |
    | Q3_Join     | SIN       |         3 |      53.026 |
    +-------------+-----------+-----------+-------------+

    +-------------+-----------+-------------+------------+
    | consulta    | escenario | promedio_ms | mediana_ms |
    +-------------+-----------+-------------+------------+
    | Q1_Igualdad | CON       |        0.11 |      0.083 |
    | Q1_Igualdad | SIN       |       2.706 |      2.453 |
    | Q2_Rango    | CON       |        3.11 |      3.005 |
    | Q2_Rango    | SIN       |       4.158 |      3.724 |
    | Q3_Join     | CON       |       24.29 |     31.699 |
    | Q3_Join     | SIN       |      37.837 |     53.026 |
    +-------------+-----------+-------------+------------+

    +-------------+-----------------+-----------------+---------------+---------------+
    | consulta    | mediana_sin_idx | mediana_con_idx | diferencia_ms | factor_mejora |
    +-------------+-----------------+-----------------+---------------+---------------+
    | Q1_Igualdad |           2.453 |           0.083 |          2.37 |         29.55 |
    | Q2_Rango    |           3.724 |           3.005 |         0.719 |          1.24 |
    | Q3_Join     |          53.026 |          31.699 |        21.327 |          1.67 |
    +-------------+-----------------+-----------------+---------------+---------------+
    ```

## ETAPA 4:

- Creacion de vistas con informacion oculta:

  - Vista para revision rapida de stock, ocultando informacion de la ficha (isbn y estanteria)

    ```
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
    ```

  - Vista para revision rapida de resumen, ocultando informacion (isbn, estanteria y editor)

    ```
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
    ```

- Creacion de usuarios y asignacion de privilegios:

  ```
  CREATE USER 'usuario_privado'@'localhost' IDENTIFIED BY 'Privado123!';
  GRANT SELECT ON tp1.vista_stock_general TO 'usuario_privado'@'localhost';
  GRANT SELECT ON tp1.vista_stock_publica TO 'usuario_privado'@'localhost';
  GRANT SELECT ON tp1.vista_resumen_publica TO 'usuario_privado'@'localhost';

  CREATE USER 'usuario_publico'@'localhost' IDENTIFIED BY 'Publico123!';
  GRANT SELECT ON tp1.vista_stock_publica TO 'usuario_publico'@'localhost';
  GRANT SELECT ON tp1.vista_resumen_publica TO 'usuario_publico'@'localhost';
  ```

* Testeo de privilegios:

  ```
  mysql -u usuario_publico -p
  USE tp1;
  SELECT * FROM vista_stock_publica LIMIT 3;
  SELECT * FROM vista_resumen_publica LIMIT 3;
  SELECT * FROM vista_stock_general LIMIT 3;
  SELECT * FROM Libro LIMIT 3;

    +----+-------------------------------+---------------------+------------------+-----------------+----------+
    | id | titulo                        | autor               | editor           | añoPublicacion  | idioma   |
    +----+-------------------------------+---------------------+------------------+-----------------+----------+
    |  1 | Más allá del horizonte 4573   | Mary Shelley        | Editorial Paidos |            1950 | Alemán   |
    |  2 | Corazones en guerra 5734      | Fiódor Dostoyevski  | Debolsillo       |            1950 | Italiano |
    |  3 | Las voces del mar 7199        | Herman Melville     | Editorial Paidos |            1950 | Francés  |
    +----+-------------------------------+---------------------+------------------+-----------------+----------+
    3 rows in set (0.000 sec)

    +----+-------------------------------+---------------------+-----------------+----------+
    | id | titulo                        | autor               | añoPublicacion  | idioma   |
    +----+-------------------------------+---------------------+-----------------+----------+
    |  1 | Más allá del horizonte 4573   | Mary Shelley        |            1950 | Alemán   |
    |  2 | Corazones en guerra 5734      | Fiódor Dostoyevski  |            1950 | Italiano |
    |  3 | Las voces del mar 7199        | Herman Melville     |            1950 | Francés  |
    +----+-------------------------------+---------------------+-----------------+----------+
    3 rows in set (0.000 sec)

    ERROR 1142 (42000): SELECT command denied to user 'usuario_publico'@'localhost' for table 'vista_stock_general'
    ERROR 1142 (42000): SELECT command denied to user 'usuario_publico'@'localhost' for table 'libro'


  mysql -u usuario_privado -p
  USE tp1;
  SELECT * FROM vista_stock_general LIMIT 3;
  SELECT * FROM vista_stock_publica LIMIT 3;
  SELECT * FROM vista_resumen_publica LIMIT 3;
  SELECT * FROM Libro LIMIT 3;

    +----+-------------------------------+---------------------+------------------+-----------------+---------------+----------+------------+
    | id | titulo                        | autor               | editor           | añoPublicacion  | isbn          | idioma   | estanteria |
    +----+-------------------------------+---------------------+------------------+-----------------+---------------+----------+------------+
    |  1 | Más allá del horizonte 4573   | Mary Shelley        | Editorial Paidos |            1950 | 000-000000001 | Alemán   |         88 |
    |  2 | Corazones en guerra 5734      | Fiódor Dostoyevski  | Debolsillo       |            1950 | 000-000000002 | Italiano |         21 |
    |  3 | Las voces del mar 7199        | Herman Melville     | Editorial Paidos |            1950 | 000-000000003 | Francés  |         28 |
    +----+-------------------------------+---------------------+------------------+-----------------+---------------+----------+------------+
    3 rows in set (0.000 sec)

    +----+-------------------------------+---------------------+------------------+-----------------+----------+
    | id | titulo                        | autor               | editor           | añoPublicacion  | idioma   |
    +----+-------------------------------+---------------------+------------------+-----------------+----------+
    |  1 | Más allá del horizonte 4573   | Mary Shelley        | Editorial Paidos |            1950 | Alemán   |
    |  2 | Corazones en guerra 5734      | Fiódor Dostoyevski  | Debolsillo       |            1950 | Italiano |
    |  3 | Las voces del mar 7199        | Herman Melville     | Editorial Paidos |            1950 | Francés  |
    +----+-------------------------------+---------------------+------------------+-----------------+----------+
    3 rows in set (0.000 sec)

    +----+-------------------------------+---------------------+-----------------+----------+
    | id | titulo                        | autor               | añoPublicacion  | idioma   |
    +----+-------------------------------+---------------------+-----------------+----------+
    |  1 | Más allá del horizonte 4573   | Mary Shelley        |            1950 | Alemán   |
    |  2 | Corazones en guerra 5734      | Fiódor Dostoyevski  |            1950 | Italiano |
    |  3 | Las voces del mar 7199        | Herman Melville     |            1950 | Francés  |
    +----+-------------------------------+---------------------+-----------------+----------+
    3 rows in set (0.000 sec)

    ERROR 1142 (42000): SELECT command denied to user 'usuario_privado'@'localhost' for table 'libro'
  ```

* Pruebas de integridad:
  1 - Duplicacion de PK

  ```
      INSERT INTO Libro (titulo, autor, editor, fechaPublicacion, eliminado)
      VALUES ('Libro PK Test', 'Autor Uno', 'Editorial Test', '2020-01-01', FALSE);

      SET @id_existente := LAST_INSERT_ID();

      INSERT INTO Libro (id, titulo, autor, editor, fechaPublicacion, eliminado)
      VALUES (@id_existente, 'Duplicado PK', 'Autor Dos', 'Editor Dos', '2021-01-01', FALSE);
  ```

  Se espera que el sistema responda con un ERROR 1062

  ```
  ERROR 1062 (23000): Duplicate entry '16384' for key 'libro.PRIMARY'
  ```

  2 - Violacion de FK

  ```
     INSERT INTO FichaBibliografica (isbn, idioma, estanteria, eliminado, libro_id)
    VALUES ('999-INVALID', 'Español', 10, FALSE, 999999999);
  ```

  Se espera que el sistema responda con un ERROR 1452

  ```
    ERROR 1452 (23000): Cannot add or update a child row: a foreign key constraint fails (`tp1`.`fichabibliografica`, CONSTRAINT `fk_ficha_libro` FOREIGN KEY (`libro_id`) REFERENCES `libro` (`id`) ON DELETE CASCADE)
  ```

  3 - Control de correcta insercion luego de errores anteriores

  ```
    INSERT INTO FichaBibliografica (isbn, idioma, estanteria, eliminado, libro_id)
    VALUES (CONCAT('978-', LPAD(FLOOR(RAND()*999999999),9,'0')), 'Español', 25, FALSE, @id_existente);

    SELECT * FROM FichaBibliografica ORDER BY id DESC LIMIT 1;

    +-------+---------------+----------+------------+-----------+----------+
    | id    | isbn          | idioma   | estanteria | eliminado | libro_id |
    +-------+---------------+----------+------------+-----------+----------+
    | 16387 | 978-543809548 | Español  |         25 |         0 |    16385 |
    +-------+---------------+----------+------------+-----------+----------+
  ```

* Consulta segura:

  Se implemento una consulta segura que permite filtrar libros por coincidencias en titulo y autor, ISBN exacto, y controlar
  el limite de registros devueltos.

  ```
    DROP PROCEDURE IF EXISTS sp_buscar_libro_seguro;
    DELIMITER //
    CREATE PROCEDURE sp_buscar_libro_seguro(
        IN p_titulo VARCHAR(150),    -- búsqueda por título (LIKE)
        IN p_autor  VARCHAR(150),    -- búsqueda por autor (igual o parcial)
        IN p_isbn   VARCHAR(30),     -- búsqueda por ISBN exacta
        IN p_limit  INT              -- límite de filas a devolver
    )
    BEGIN
    -- Ajuste seguro de límite
    IF p_limit IS NULL OR p_limit <= 0 OR p_limit > 1000 THEN
        SET p_limit = 50;
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
        AND (p_isbn IS NULL OR f.isbn = p_isbn)                           -- coincidencia exacta por ISBN (si se pasa)
        AND (p_titulo IS NULL OR l.titulo LIKE CONCAT('%', p_titulo, '%')) -- búsqueda por título segura
        AND (p_autor  IS NULL OR l.autor  LIKE CONCAT('%', p_autor, '%'))  -- búsqueda por autor segura
    ORDER BY l.fechaPublicacion DESC
    LIMIT p_limit;
    END //
    DELIMITER ;
  ```

  - 1. Busqueda por titulo parcial

    ```
    CALL sp_buscar_libro_seguro('Corazones', NULL, NULL, 10);

    +------+--------------------------+---------------------------+--------------------+-----------------+------------+
    | id   | titulo                   | autor                     | editor             | anioPublicacion | idioma     |
    +------+--------------------------+---------------------------+--------------------+-----------------+------------+
    | 9929 | Corazones en guerra 1810 | Antoine de Saint-Exupéry  | Editorial Paidos   |            1977 | Inglés     |
    | 9926 | Corazones en guerra 8292 | Ernesto Sábato            | Siglo XXI Editores |            1977 | Portugués  |
    | 9877 | Corazones en guerra 863  | Jane Austen               | Siglo XXI Editores |            1977 | Italiano   |
    | 9822 | Corazones en guerra 3565 | Fiódor Dostoyevski        | Emecé              |            1976 | Francés    |
    | 9813 | Corazones en guerra 1232 | George Orwell             | Bompiani           |            1976 | Inglés     |
    | 9806 | Corazones en guerra 7351 | Fiódor Dostoyevski        | Viking Press       |            1976 | Portugués  |
    | 9797 | Corazones en guerra 1093 | Bram Stoker               | Planeta            |            1976 | Inglés     |
    | 9777 | Corazones en guerra 9442 | Jorge Luis Borges         | Debolsillo         |            1976 | Portugués  |
    | 9771 | Corazones en guerra 1300 | Roberto Bolaño            | Ballantine Books   |            1976 | Portugués  |
    | 9765 | Corazones en guerra 2353 | Fiódor Dostoyevski        | Alfaguara          |            1976 | Español    |
    +------+--------------------------+---------------------------+--------------------+-----------------+------------+
    10 rows in set (0.002 sec)
    ```

    - 2. Busqueda por ISBN

    ```
    CALL sp_buscar_libro_seguro(NULL, NULL, '000-000000003', 10);

    +----+------------------------+-----------------+------------------+-----------------+----------+
    | id | titulo                 | autor           | editor           | anioPublicacion | idioma   |
    +----+------------------------+-----------------+------------------+-----------------+----------+
    |  3 | Las voces del mar 7199 | Herman Melville | Editorial Paidos |            1950 | Francés  |
    +----+------------------------+-----------------+------------------+-----------------+----------+
    1 row in set (0.047 sec)
    ```

    - 3. Intento clásico de inyección por cadena de SQL en el titulo

    ```
    CALL sp_buscar_libro_seguro("'; DROP TABLE Libro; --", NULL, NULL, 10);

    Empty set (0.013 sec)
    ```

    - 4. Intento SQL injection clásico

    ```
    CALL sp_buscar_libro_seguro("anything' OR '1'='1", NULL, NULL, 10);

    Empty set (0.013 sec)
    ```

    - 5. Intento de manipulación de LIMIT (no funciona porque p_limit es parámetro entero controlado)

    ```
    CALL sp_buscar_libro_seguro(NULL, NULL, NULL, 1000000);

    +-------+-----------------------------------+---------------------------+---------------------------+-----------------+------------+
    | id    | titulo                            | autor                     | editor                    | anioPublicacion | idioma     |
    +-------+-----------------------------------+---------------------------+---------------------------+-----------------+------------+
    | 16385 | Libro PK Test                     | Autor Uno                 | Editorial Test            |            2020 | Español    |
    | 16384 | Libro PK Test                     | Autor Uno                 | Editorial Test            |            2020 | NULL       |
    | 10000 | Los días dorados 2085             | Miguel de Cervantes       | Editorial Paidos          |            1977 | Italiano   |
    ...
    |  9953 | La última carta 8910              | James Joyce               | Charles Scribner’s Sons   |            1977 | Alemán     |
    +-------+-----------------------------------+---------------------------+---------------------------+-----------------+------------+
    50 rows in set (0.000 sec)
    ```

    - 6. Verificación que no se modifico la tabla Libro

    ```
    SHOW TABLES LIKE 'Libro';
    SELECT COUNT(\*) AS total_libros FROM Libro;

    +-----------------------+
    | Tables_in_tp1 (Libro) |
    +-----------------------+
    | Libro                 |
    +-----------------------+
    1 row in set (0.001 sec)

    +--------------+
    | total_libros |
    +--------------+
    |        10002 |
    +--------------+
    1 row in set (0.001 sec)
    ```
