# TP6 – SGBDR: DDL y DML (borrador para reformular)

Scripts probados en MariaDB 10.11 (mismo motor que XAMPP):
- `act1_libreria.sql` → Actividad 1 completa
- `act2_aerolinea.sql` → Actividad 2 completa

---

## Actividad 1 – Librería

**a) Replicar la creación en phpMyAdmin con capturas.**
Orden: `articulos` → `detalle_factura` (solo FK a artículos) → `localidades` → `clientes` → `facturas` → `ALTER TABLE detalle_factura` para la FK a facturas.
Ajustes respecto del ejemplo:
- `Precio` como `DECIMAL(10,2)` (el ejemplo pone `INT` con longitud `10,2`, que no es válido para decimales).
- Códigos de artículo unificados como `n-0023_D`: la figura mezcla `-D` y `_D` y la FK de `2-0023-D` fallaría.

**b) Cargar los datos de la figura.**
Orden por FK: localidades → clientes → articulos → facturas → detalle_factura. Fechas en formato `AAAA-MM-DD`.

**c) Ver DDL de tablas e índices.**
`SHOW CREATE TABLE <tabla>;` y `SHOW INDEX FROM <tabla>;`
InnoDB crea un índice por cada PK y por cada FK (ej. `fk_cp` en clientes).

**d) Clientes que compraron el 02/08/2024.**
```sql
SELECT DISTINCT c.Numero_Cliente, c.Apellido_Cliente, c.Nombre_Cliente
FROM clientes c
INNER JOIN facturas f ON f.Numero_Cliente = c.Numero_Cliente
WHERE f.Fecha = '2024-08-02';
```
Resultado: 22 Martinez Natalia, 23 Garcia Carlos.

---

## Actividad 2 – Aerolínea

**a) Analizar el modelo.**
- Clientes 1:N Reservas (`codigo_cliente`)
- Vuelos 1:N Reservas (`id_vuelo`)
- Destinos 1:N Vuelos (`id_destino`)
- Avion 1:N Vuelos (`codigo_avion`)
- Horarios 1:N Destinos (`id_horario`)

Reservas funciona como entidad asociativa entre Clientes y Vuelos (N:M).

**b) Crear la base en phpMyAdmin.** Ver DDL del script (mismo orden que el punto c).

**c) Orden de carga según FK.**
1. Horarios, Avion, Clientes (no dependen de nadie, cualquier orden)
2. Destinos (necesita Horarios)
3. Vuelos (necesita Avion y Destinos)
4. Reservas (necesita Vuelos y Clientes)

Regla: primero las tablas referenciadas, después las que tienen la FK. Para borrar, al revés.

**d) Insertar 5 pasajeros y 8 reservas (último = Agustín Lago).** Ver bloque `INSERT` del script.

**e) Ver DDL e índices.** `SHOW CREATE TABLE` + `SHOW INDEX FROM` por tabla.

**f) Plan de ejecución: vuelos y clientes de un destino.**
```sql
EXPLAIN
SELECT v.id_vuelo, v.tipo_vuelo, c.codigo, c.apellido, c.nombre
FROM destinos d
INNER JOIN vuelos v   ON v.id_destino = d.id_destino
INNER JOIN reservas r ON r.id_vuelo = v.id_vuelo
INNER JOIN clientes c ON c.codigo = r.codigo_cliente
WHERE d.nombre = 'Bariloche';
```
Lectura: `destinos` hace `ALL` (no hay índice en `nombre`); vuelos y clientes entran por PK (`eq_ref`, 1 fila). Con pocas filas el optimizador prefiere escanear.

**g) Clientes por apellido y nombre + índice.**
- Sin índice: `type = ALL`, `Extra = Using filesort` → ordena en memoria después de leer toda la tabla.
- `CREATE INDEX idx_cliente_apellido_nombre ON clientes (apellido, nombre);`
- Con índice: `type = index`, `Using index` → recorre el índice ya ordenado, sin filesort.

**h) Vista ordenada + consultarla ordenando solo por nombre.**
- `EXPLAIN SELECT * FROM v_clientes_ordenados;` → usa el índice, sin filesort.
- `EXPLAIN SELECT * FROM v_clientes_ordenados ORDER BY nombre;` → `Using index; Using filesort`.

Conclusión: el `ORDER BY` externo reemplaza al de la vista, y el índice `(apellido, nombre)` solo sirve para ordenar desde su primera columna (prefijo izquierdo). Ordenar solo por `nombre` vuelve a requerir filesort. Para ese caso habría que crear un índice que empiece por `nombre`.

---

## Capturas a sacar (entrega)
1. Creación de cada base (nombre + `utf8mb4_spanish_ci`).
2. Estructura de cada tabla (pestaña Estructura) + Vista de relaciones con las FK.
3. Ejecución de los INSERT (pestaña SQL, mensaje de filas insertadas).
4. Resultados de `SHOW CREATE TABLE` / `SHOW INDEX`.
5. Consulta 1d con resultado.
6. Los 4 `EXPLAIN` de f, g (antes/después) y h.
