# TP6 – Lista de pasos completa (📸 = captura)

## Preparación
1. XAMPP → Start **Apache** y **MySQL** → Admin (MySQL) → abre phpMyAdmin.

## Actividad 1 – Librería

### a) Creación a mano
2. **New** → nombre `libreria`, cotejamiento `utf8mb4_spanish_ci` → 📸 → Create.
3. Tabla `articulos` (3 col): `Codigo_Articulo` VARCHAR(8) PRIMARY · `Articulo` VARCHAR(100) · `Precio` DECIMAL(10,2) → 📸 → Save.
4. Tabla `detalle_factura` (4 col): `Numero_Factura` INT · `Item_Factura` INT · `Codigo_Articulo` VARCHAR(8) · `Unidades` INT → Save.
5. Structure → tildar `Numero_Factura` + `Item_Factura` → **Primary** → 📸 (las dos con llave).
6. Relation view → `fk_detalle_articulo`: `Codigo_Articulo` → `articulos.Codigo_Articulo`, RESTRICT/RESTRICT → 📸 → Save.
7. Tabla `localidades` (2 col): `Codigo_Postal` INT PRIMARY · `Localidad` VARCHAR(100) → 📸 → Save.
8. Tabla `clientes` (6 col): `Numero_Cliente` INT PRIMARY · `Apellido_Cliente` VARCHAR(60) · `Nombre_Cliente` VARCHAR(120) · `Calle` VARCHAR(60) · `Numero_Calle` INT · `Codigo_Postal` INT → 📸 → Save.
9. Relation view → `fk_cp`: `Codigo_Postal` → `localidades.Codigo_Postal` → 📸 → Save.
10. Tabla `facturas` (3 col): `Numero_Factura` INT PRIMARY · `Fecha` DATE · `Numero_Cliente` INT → 📸 → Save.
11. Relation view → `fk_factura_cliente`: `Numero_Cliente` → `clientes.Numero_Cliente` → 📸 → Save.
12. `detalle_factura` → Relation view → `fk_detalle_factura`: `Numero_Factura` → `facturas.Numero_Factura` → **Preview SQL** 📸 (ALTER TABLE) → Save → 📸 con las 2 FK.
13. Base `libreria` → More → **Designer** → 📸 del diagrama.

### b) Carga de datos
14. Base `libreria` → pestaña **SQL** → pegar los `INSERT` de `act1_libreria.sql` (localidades → clientes → articulos → facturas → detalle_factura) → Go → 📸 del mensaje de filas insertadas.
15. Examinar (Browse) cada tabla → 📸 de cada una con los datos.

### c) DDL e índices
16. SQL → pegar los `SHOW CREATE TABLE` → Go → 📸 (usar "+ Options" → Full texts para ver el SQL completo).
17. SQL → pegar los `SHOW INDEX FROM` → Go → 📸.

### d) Consulta
18. SQL → pegar el `SELECT` de compras del 02/08/2024 → Go → 📸 (debe dar Martinez y Garcia).

## Actividad 2 – Aerolínea (por bloques de `act2_aerolinea.sql`)

### b) Crear la base
19. SQL (servidor) → pegar desde `DROP DATABASE` hasta el `CREATE TABLE reservas` incluido → Go → 📸.
20. Por cada tabla: Structure 📸 + Relation view 📸 (destinos, vuelos, reservas tienen FK).
21. More → Designer → 📸 del diagrama.

### c) Orden de carga
22. Texto (sin captura): Horarios, Avion, Clientes → Destinos → Vuelos → Reservas.

### d) Inserts
23. SQL → pegar todos los `INSERT` → Go → 📸.
24. Browse `clientes` (se ve Agustín Lago) 📸 y `reservas` 📸.

### e) DDL e índices
25. SQL → `SHOW CREATE TABLE` de las 6 tablas → 📸.
26. SQL → `SHOW INDEX FROM` de las 6 tablas → 📸.

### f) Plan por destino
27. SQL → pegar el `EXPLAIN SELECT ... WHERE d.nombre = 'Bariloche'` → 📸.

### g) Orden + índice (respetar el orden)
28. SQL → `SELECT ... ORDER BY apellido, nombre` → 📸.
29. SQL → `EXPLAIN` de esa consulta → 📸 (Using filesort).
30. SQL → `CREATE INDEX idx_cliente_apellido_nombre ...` → 📸.
31. SQL → mismo `EXPLAIN` → 📸 (Using index).

### h) Vista
32. SQL → `CREATE OR REPLACE VIEW v_clientes_ordenados ...` → 📸.
33. SQL → `EXPLAIN SELECT * FROM v_clientes_ordenados;` → 📸.
34. SQL → `EXPLAIN SELECT * FROM v_clientes_ordenados ORDER BY nombre;` → 📸 (vuelve filesort).
35. Escribir conclusión (ver `respuestas.md`).

## Entrega
36. Guardar las capturas en `TP6_SGBDR\capturas\` numeradas (`01_crear_base.png`, ...).
37. Redactar respuestas reformuladas con la bibliografía (base: `respuestas.md`).
38. Armar PDF: Portada · Introducción · Consignas · Desarrollo (capturas + SQL + respuestas) · Conclusión.
