"""Genera el informe del TP6 (SGBDR: DDL y DML) con la plantilla utn_template."""
import os
import sys

from PIL import Image as PILImage
from reportlab.platypus import KeepTogether

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
from utn_template import Doc, S as TS  # noqa: E402

# Evitamos títulos huérfanos al pie de página
for k in ("h1", "sub", "consigna"):
    TS[k].keepWithNext = 1

BASE = os.path.dirname(os.path.abspath(__file__))
CAPS = sys.argv[1] if len(sys.argv) > 1 else os.path.join(BASE, "capturas")
OUT = sys.argv[2] if len(sys.argv) > 2 else os.path.join(BASE, "TP6_SGBDR_Lago.pdf")
COL_W = 451

d = Doc(OUT)
# Definimos los metadatos que muestra el visor como título del PDF
d.doc.title = "TP6 - Sistemas de Gestión de Bases de Datos Relacionales"
d.doc.author = "Agustín Lago"
d.doc.subject = "Base de Datos I"


def shot(name, label, scale=0.6):
    """Agregamos rótulo + captura juntos, sin agrandar capturas chicas."""
    path = os.path.join(CAPS, name)
    px_w = PILImage.open(path).size[0]
    start = len(d.f)
    d.label(label)
    d.image(path, width=min(COL_W, px_w * scale))
    block = d.f[start:]
    del d.f[start:]
    d.f.append(KeepTogether(block))


def code(text):
    d.label("Código fuente:")
    # Partimos bloques largos por grupos de sentencias para que entren en una página
    chunks, cur = [], []
    for group in text.strip("\n").split("\n\n"):
        lines = group.split("\n")
        if cur and len(cur) + len(lines) > 40:
            chunks.append("\n".join(cur))
            cur = []
        cur += ([""] if cur else []) + lines
    chunks.append("\n".join(cur))
    # Mantenemos el rótulo junto al primer bloque de código
    start = len(d.f) - 1
    d.code(chunks[0])
    block = d.f[start:]
    del d.f[start:]
    d.f.append(KeepTogether(block))
    for c in chunks[1:]:
        d.code(c)


# ---------------------------------------------------------------- Portada
d.cover("Trabajo Práctico",
        "Sistemas de Gestión de Bases de Datos Relacionales",
        "Base de Datos I",
        ["Agustín Lago – lagoagustindev@gmail.com"],
        "Neira Sergio Mauricio")

# ---------------------------------------------------------------- 1. Introducción
d.h1("1. Introducción")
d.p("El siguiente trabajo tiene como objetivo implementar dos bases de datos relacionales en un "
    "**Sistema de Gestión de Bases de Datos Relacional** (SGBDR) a partir de sus modelos conceptuales. "
    "Se utilizó phpMyAdmin sobre el motor MariaDB incluido en XAMPP para definir tablas, claves primarias, "
    "claves foráneas e índices mediante sentencias **DDL**, y para cargar datos mediante sentencias **DML**. "
    "Se analizaron además los planes de ejecución de distintas consultas para observar el efecto de los "
    "índices y de las vistas sobre el ordenamiento de los resultados.")

# ---------------------------------------------------------------- 2. Consignas
d.h1("2. Consignas")
d.sub("Actividad 1")
d.numbered([
    "Replicar los pasos de las consideraciones iniciales (creación de la base Librería y sus tablas), "
    "realizando capturas de pantalla de los pasos ejecutados.",
    "Cargar los datos de la figura en las tablas correspondientes.",
    "Ejecutar las sentencias necesarias para visualizar las consultas SQL de creación de tablas e índices "
    "realizadas en la actividad anterior.",
    "Realizar una consulta SQL sobre la base que muestre qué clientes realizaron compras el 02/08/2024.",
])
d.sub("Actividad 2")
d.numbered([
    "Analizar la base de datos de la figura con sus tablas y relaciones.",
    "Crear la base de datos con phpMyAdmin.",
    "Describir en qué orden se pueden completar las tablas en relación con las restricciones de FK presentes.",
    "Escribir las sentencias SQL para insertar datos de al menos 5 pasajeros y 8 reservas; el último pasajero "
    "debe tener el nombre y apellido propio. Ejecutar las sentencias en phpMyAdmin y capturar las pantallas.",
    "Ejecutar las sentencias SQL necesarias para visualizar las queries de creación de tablas e índices.",
    "Ejecutar las sentencias necesarias para visualizar los planes de ejecución para listar los vuelos y "
    "clientes de un determinado destino.",
    "Listar los clientes ordenados por apellido y nombre, ver el plan de ejecución, analizar la situación "
    "agregando un índice y volver a ejecutar el plan.",
    "Generar una vista ordenada por ese criterio, ver el plan de ejecución de la vista ordenando solo por "
    "nombre, no por apellido, y obtener una conclusión.",
])

# ---------------------------------------------------------------- 3. Desarrollo
d.h1("3. Desarrollo")
d.sub("Actividad 1: Base de datos Librería")

# 1.a
d.consigna("Consigna 1.a: Creación de la base, tablas y restricciones")
d.p("Se creó la base libreria con el cotejamiento utf8mb4_spanish_ci y luego cada tabla desde la interfaz "
    "de phpMyAdmin. Se respetó el orden del ejemplo: la **foreign key** de detalle_factura hacia facturas se "
    "agregó al final mediante ALTER TABLE, ya que la tabla referenciada todavía no existía al momento de "
    "crear el detalle.")
d.bullets([
    "**Precio:** Se definió como DECIMAL(10,2), ya que el tipo INT no admite la parte decimal indicada en el ejemplo.",
    "**Código de artículo:** Se unificó el formato n-0023_D, porque la figura alterna guion medio y guion bajo "
    "y esa diferencia impide la validación de la clave foránea.",
    "**Restricciones:** Todas las claves foráneas se definieron con ON DELETE RESTRICT y ON UPDATE RESTRICT.",
])
shot("1a_01_crear_base.png", "Creación de la base de datos:")
shot("1a_02_tabla_articulos.png", "Tabla articulos y su clave primaria:")
shot("1a_03_detalle_pk.png", "Tabla detalle_factura con clave primaria compuesta:")
shot("1a_04_detalle_fk_articulos.png", "Clave foránea de detalle_factura hacia articulos:")
shot("1a_06_tabla_localidades.png", "Tabla localidades:")
shot("1a_05_tabla_clientes.png", "Tabla clientes con su clave primaria y la clave foránea fk_cp:")
shot("1a_07_tabla_facturas.png", "Tabla facturas:")
shot("1a_10_facturas_fk_cliente.png", "Clave foránea de facturas hacia clientes:")
shot("1a_08_detalle_fk_factura.png", "Restricción agregada luego en detalle_factura hacia facturas:")
shot("1a_09_designer_libreria.png", "Diagrama resultante (Designer):", scale=0.7)

# 1.b
d.consigna("Consigna 1.b: Carga de datos")
d.p("Se cargaron los datos respetando el orden impuesto por las claves foráneas: primero las tablas "
    "referenciadas y luego las que las referencian.")
code("""
-- Insertamos primero las tablas sin dependencias
INSERT INTO localidades (Codigo_Postal, Localidad) VALUES
    (1870, 'Avellaneda'),
    (1900, 'La Plata');

INSERT INTO clientes (Numero_Cliente, Apellido_Cliente, Nombre_Cliente,
                      Calle, Numero_Calle, Codigo_Postal) VALUES
    (21, 'Rodriguez', 'Facundo', 'Rivero', 41, 1870),
    (22, 'Martinez', 'Natalia', 'San Eloy', 82, 1870),
    (23, 'Garcia', 'Carlos', 'Av. De Italia', 245, 1900);

INSERT INTO articulos (Codigo_Articulo, Articulo, Precio) VALUES
    ('1-0023_D', 'Auriculares inalambricos', 175),
    ('2-0023_D', 'Teclado inalambrico', 245),
    ('3-0023_D', 'Mini adaptador USB-C USB 2.0', 75),
    ('4-0023_D', 'Cable Red UTP RJ45', 90),
    ('5-0023_D', 'Camara Web USB', 165);

-- Cargamos facturas y por ultimo su detalle
INSERT INTO facturas (Numero_Factura, Fecha, Numero_Cliente) VALUES
    (335, '2024-08-01', 21),
    (336, '2024-08-02', 22),
    (337, '2024-08-02', 23);

INSERT INTO detalle_factura (Numero_Factura, Item_Factura,
                             Codigo_Articulo, Unidades) VALUES
    (335, 1, '2-0023_D', 10),
    (335, 2, '4-0023_D', 12),
    (335, 3, '5-0023_D', 1),
    (336, 1, '1-0023_D', 2),
    (336, 2, '3-0023_D', 2),
    (337, 1, '2-0023_D', 25);
""")
shot("1b_01_inserts.png", "Ejecución de las sentencias:")
shot("1b_06_localidades.png", "Contenido de localidades:")
shot("1b_03_clientes.png", "Contenido de clientes:")
shot("1b_02_articulos.png", "Contenido de articulos:")
shot("1b_05_facturas.png", "Contenido de facturas:")
shot("1b_04_detalle_factura.png", "Contenido de detalle_factura:")

# 1.c
d.consigna("Consigna 1.c: Sentencias de creación de tablas e índices")
code("""
-- Visualizamos la sentencia de creacion de cada tabla
SHOW CREATE TABLE localidades;
SHOW CREATE TABLE clientes;
SHOW CREATE TABLE articulos;
SHOW CREATE TABLE facturas;
SHOW CREATE TABLE detalle_factura;

-- Visualizamos los indices de cada tabla
SHOW INDEX FROM localidades;
SHOW INDEX FROM clientes;
SHOW INDEX FROM articulos;
SHOW INDEX FROM facturas;
SHOW INDEX FROM detalle_factura;
""")
for n, t in [("1c_01_create_localidades.png", "localidades"), ("1c_02_create_clientes.png", "clientes"),
             ("1c_03_create_articulos.png", "articulos"), ("1c_04_create_facturas.png", "facturas"),
             ("1c_05_create_detalle_factura.png", "detalle_factura")]:
    shot(n, f"SHOW CREATE TABLE {t}:")
for n, t in [("1c_06_index_localidades.png", "localidades"), ("1c_07_index_clientes.png", "clientes"),
             ("1c_08_index_articulos.png", "articulos"), ("1c_09_index_facturas.png", "facturas"),
             ("1c_10_index_detalle_factura.png", "detalle_factura")]:
    shot(n, f"SHOW INDEX FROM {t}:")
d.p("InnoDB genera un índice por cada clave primaria y por cada clave foránea. En detalle_factura no aparece "
    "un índice propio para la clave foránea hacia facturas, ya que Numero_Factura es la primera columna de la "
    "clave primaria compuesta y el motor reutiliza ese índice.")

# 1.d
d.consigna("Consigna 1.d: Clientes con compras el 02/08/2024")
code("""
-- Unimos clientes con facturas y filtramos por fecha
SELECT DISTINCT c.Numero_Cliente, c.Apellido_Cliente, c.Nombre_Cliente
FROM clientes c
INNER JOIN facturas f ON f.Numero_Cliente = c.Numero_Cliente
WHERE f.Fecha = '2024-08-02';
""")
shot("1d_01_consulta_fecha.png", "Resultado:")
d.p("La consulta devuelve a Natalia Martinez (factura 336) y a Carlos Garcia (factura 337). Se utilizó "
    "DISTINCT para evitar filas repetidas en caso de que un cliente tenga más de una factura en la misma fecha.")

d.sub("Actividad 2: Base de datos Aerolínea")

# 2.a
d.consigna("Consigna 2.a: Análisis del modelo")
d.p("El modelo está compuesto por seis tablas. Reservas funciona como entidad asociativa entre Clientes y "
    "Vuelos, lo que resuelve una relación de muchos a muchos: un cliente puede tener varias reservas y un "
    "vuelo puede ser reservado por varios clientes.")
d.table(["Tabla", "Clave foránea", "Referencia", "Cardinalidad"], [
    ["reservas", "codigo_cliente", "clientes.codigo", "1:N"],
    ["reservas", "id_vuelo", "vuelos.id_vuelo", "1:N"],
    ["vuelos", "id_destino", "destinos.id_destino", "1:N"],
    ["vuelos", "codigo_avion", "avion.codigo", "1:N"],
    ["destinos", "id_horario", "horarios.id_horario", "1:N"],
], [95, 120, 146, 90])

# 2.b
d.consigna("Consigna 2.b: Creación de la base")
d.p("Se creó la base mediante sentencias DDL ejecutadas desde la pestaña SQL de phpMyAdmin, definiendo las "
    "tablas en orden de dependencia para que cada clave foránea referencie una tabla ya existente.")
code("""
CREATE DATABASE aerolinea CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE aerolinea;

-- Definimos primero las tablas sin claves foraneas
CREATE TABLE horarios (
    id_horario INT NOT NULL,
    hora_vuelo VARCHAR(20) NOT NULL,
    PRIMARY KEY (id_horario)
) ENGINE=InnoDB;

CREATE TABLE avion (
    codigo INT NOT NULL,
    marca VARCHAR(50) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    PRIMARY KEY (codigo)
) ENGINE=InnoDB;

CREATE TABLE clientes (
    codigo INT NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    usuario VARCHAR(20) NOT NULL,
    PRIMARY KEY (codigo)
) ENGINE=InnoDB;

-- Destinos depende de horarios
CREATE TABLE destinos (
    id_destino INT NOT NULL,
    id_horario INT NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_destino),
    CONSTRAINT fk_destino_horario FOREIGN KEY (id_horario)
        REFERENCES horarios (id_horario)
        ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

-- Vuelos depende de avion y destinos
CREATE TABLE vuelos (
    id_vuelo INT NOT NULL,
    disponibilidad VARCHAR(20) NOT NULL,
    tipo_vuelo VARCHAR(20) NOT NULL,
    codigo_avion INT NOT NULL,
    id_destino INT NOT NULL,
    PRIMARY KEY (id_vuelo),
    CONSTRAINT fk_vuelo_avion FOREIGN KEY (codigo_avion)
        REFERENCES avion (codigo)
        ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_vuelo_destino FOREIGN KEY (id_destino)
        REFERENCES destinos (id_destino)
        ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

-- Reservas depende de vuelos y clientes
CREATE TABLE reservas (
    id_reserva INT NOT NULL,
    estado VARCHAR(20) NOT NULL,
    id_vuelo INT NOT NULL,
    codigo_cliente INT NOT NULL,
    PRIMARY KEY (id_reserva),
    CONSTRAINT fk_reserva_vuelo FOREIGN KEY (id_vuelo)
        REFERENCES vuelos (id_vuelo)
        ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_reserva_cliente FOREIGN KEY (codigo_cliente)
        REFERENCES clientes (codigo)
        ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;
""")
shot("2b_02_designer_aerolinea.png", "Diagrama resultante (Designer):", scale=0.7)
shot("2b_03_destinos.png", "Estructura e índices de destinos:")
shot("2b_05_vuelos.png", "Estructura e índices de vuelos:")
shot("2b_04_reservas.png", "Estructura e índices de reservas:")

# 2.c
d.consigna("Consigna 2.c: Orden de carga según las claves foráneas")
d.p("Una fila con clave foránea solo puede insertarse si el valor referenciado ya existe en la tabla padre. "
    "Por lo tanto, las tablas se completan desde las que no dependen de ninguna otra hacia las que concentran "
    "más referencias:")
d.numbered([
    "**Horarios, Avion y Clientes:** No poseen claves foráneas, por lo que pueden cargarse en cualquier orden.",
    "**Destinos:** Requiere que exista el horario referenciado.",
    "**Vuelos:** Requiere que existan el avión y el destino.",
    "**Reservas:** Requiere que existan el vuelo y el cliente.",
])
d.p("Para eliminar datos se aplica el orden inverso, ya que las restricciones RESTRICT impiden borrar una "
    "fila padre mientras existan filas hijas que la referencien.")

# 2.d
d.consigna("Consigna 2.d: Inserción de pasajeros y reservas")
code("""
-- Cargamos las tablas independientes
INSERT INTO horarios (id_horario, hora_vuelo) VALUES
    (1, '06:30'), (2, '12:15'), (3, '18:45');

INSERT INTO avion (codigo, marca, estado) VALUES
    (101, 'Boeing', 'Operativo'),
    (102, 'Airbus', 'Operativo'),
    (103, 'Embraer', 'Mantenimiento');

INSERT INTO clientes (codigo, nombre, apellido,
                      fecha_nacimiento, tipo, usuario) VALUES
    (1, 'Lucia', 'Fernandez', '1990-03-14', 'Frecuente', 'lfernandez'),
    (2, 'Martin', 'Sosa', '1985-11-02', 'Estandar', 'msosa'),
    (3, 'Carla', 'Benitez', '1998-07-21', 'Premium', 'cbenitez'),
    (4, 'Diego', 'Acosta', '1979-01-30', 'Estandar', 'dacosta'),
    (5, 'Agustin', 'Lago', '1995-05-10', 'Frecuente', 'alago');

-- Cargamos destinos y vuelos, que dependen de las anteriores
INSERT INTO destinos (id_destino, id_horario, nombre) VALUES
    (1, 1, 'Bariloche'), (2, 2, 'Mendoza'), (3, 3, 'Salta');

INSERT INTO vuelos (id_vuelo, disponibilidad, tipo_vuelo,
                    codigo_avion, id_destino) VALUES
    (1001, 'Disponible', 'Nacional', 101, 1),
    (1002, 'Completo', 'Nacional', 102, 1),
    (1003, 'Disponible', 'Nacional', 102, 2),
    (1004, 'Disponible', 'Nacional', 101, 3);

-- Por ultimo, las reservas
INSERT INTO reservas (id_reserva, estado, id_vuelo, codigo_cliente) VALUES
    (1, 'Confirmada', 1001, 1),
    (2, 'Confirmada', 1001, 5),
    (3, 'Pendiente', 1002, 2),
    (4, 'Cancelada', 1003, 3),
    (5, 'Confirmada', 1003, 4),
    (6, 'Confirmada', 1004, 5),
    (7, 'Pendiente', 1002, 3),
    (8, 'Confirmada', 1004, 1);
""")
shot("2d_01_inserts.png", "Ejecución de las sentencias:")
shot("2d_02_clientes.png", "Contenido de clientes:")
shot("2d_03_reservas.png", "Contenido de reservas:")

# 2.e
d.consigna("Consigna 2.e: Sentencias de creación de tablas e índices")
code("""
-- Visualizamos la sentencia de creacion de cada tabla
SHOW CREATE TABLE horarios;
SHOW CREATE TABLE avion;
SHOW CREATE TABLE clientes;
SHOW CREATE TABLE destinos;
SHOW CREATE TABLE vuelos;
SHOW CREATE TABLE reservas;

-- Visualizamos los indices de cada tabla
SHOW INDEX FROM horarios;
SHOW INDEX FROM avion;
SHOW INDEX FROM clientes;
SHOW INDEX FROM destinos;
SHOW INDEX FROM vuelos;
SHOW INDEX FROM reservas;
""")
for n, t in [("2e_01_create_horarios.png", "horarios"), ("2e_02_create_avion.png", "avion"),
             ("2e_03_create_clientes.png", "clientes"), ("2e_04_create_destinos.png", "destinos"),
             ("2e_05_create_vuelos.png", "vuelos"), ("2e_06_create_reservas.png", "reservas")]:
    shot(n, f"SHOW CREATE TABLE {t}:")
shot("2e_07_indices.png", "SHOW INDEX de todas las tablas:", scale=0.35)

# 2.f
d.consigna("Consigna 2.f: Plan de ejecución de vuelos y clientes por destino")
d.p("El **plan de ejecución** describe cómo el motor resuelve una consulta: el orden de acceso a las tablas, "
    "los índices que utiliza y la cantidad estimada de filas que examina. Se obtiene anteponiendo EXPLAIN a "
    "la consulta.")
code("""
-- Obtenemos el plan de ejecucion para el destino Bariloche
EXPLAIN
SELECT v.id_vuelo, v.tipo_vuelo, c.codigo, c.apellido, c.nombre
FROM destinos d
INNER JOIN vuelos v   ON v.id_destino = d.id_destino
INNER JOIN reservas r ON r.id_vuelo = v.id_vuelo
INNER JOIN clientes c ON c.codigo = r.codigo_cliente
WHERE d.nombre = 'Bariloche';
""")
shot("2f_01_explain_destino.png", "Plan de ejecución:")
d.bullets([
    "**destinos (ALL):** Se recorre la tabla completa, porque la columna nombre no posee índice.",
    "**reservas (ALL):** Con pocas filas, el optimizador prefiere un recorrido completo con join buffer antes "
    "que usar los índices de sus claves foráneas.",
    "**clientes y vuelos (eq_ref):** Se accede por clave primaria, leyendo una única fila por cada reserva.",
])

# 2.g
d.consigna("Consigna 2.g: Ordenamiento por apellido y nombre con índice")
code("""
-- Listamos y analizamos el plan sin indice
SELECT codigo, apellido, nombre FROM clientes ORDER BY apellido, nombre;
EXPLAIN SELECT codigo, apellido, nombre FROM clientes ORDER BY apellido, nombre;

-- Creamos un indice compuesto y repetimos el plan
CREATE INDEX idx_cliente_apellido_nombre ON clientes (apellido, nombre);
EXPLAIN SELECT codigo, apellido, nombre FROM clientes ORDER BY apellido, nombre;
""")
shot("2g_01_select_orden.png", "Listado ordenado:")
shot("2g_02_explain_sin_indice.png", "Plan sin índice:")
shot("2g_03_create_index.png", "Creación del índice:")
shot("2g_04_explain_con_indice.png", "Plan con índice:")
d.table(["Situación", "type", "key", "Extra"], [
    ["Sin índice", "ALL", "NULL", "Using filesort"],
    ["Con índice (apellido, nombre)", "index", "idx_cliente_apellido_nombre", "Using index"],
], [130, 55, 166, 100])
d.p("Sin índice, el motor lee toda la tabla y ordena las filas en memoria (**filesort**). Con el índice "
    "compuesto, recorre el índice, que ya almacena las claves ordenadas por apellido y nombre, y elimina la "
    "operación de ordenamiento. Además, como el índice contiene todas las columnas consultadas, la consulta se "
    "resuelve sin acceder a las filas de la tabla (Using index).")

# 2.h
d.consigna("Consigna 2.h: Vista ordenada y ordenamiento solo por nombre")
code("""
-- Creamos la vista ordenada por apellido y nombre
CREATE OR REPLACE VIEW v_clientes_ordenados AS
SELECT codigo, apellido, nombre FROM clientes ORDER BY apellido, nombre;

-- Comparamos el plan de la vista con y sin un orden externo por nombre
EXPLAIN SELECT * FROM v_clientes_ordenados;
EXPLAIN SELECT * FROM v_clientes_ordenados ORDER BY nombre;
""")
shot("2h_01_create_view.png", "Creación de la vista:")
shot("2h_02_explain_vista.png", "Plan de la vista:")
shot("2h_03_explain_vista_nombre.png", "Plan de la vista ordenando solo por nombre:")
d.p("Al consultar la vista sin un orden adicional, el motor utiliza el índice y no necesita ordenar. Al "
    "aplicar ORDER BY nombre, el orden externo reemplaza al definido en la vista y vuelve a aparecer Using "
    "filesort. El índice (apellido, nombre) solo permite ordenar a partir de su primera columna (prefijo "
    "izquierdo), por lo que no resuelve un orden basado únicamente en nombre: el índice se sigue usando para "
    "leer los datos, pero no para ordenarlos. Para ese criterio sería necesario un índice que comience por la "
    "columna nombre.")

# ---------------------------------------------------------------- 4. Conclusiones
d.h1("4. Conclusiones")
d.p("La implementación de ambas bases permitió traducir los modelos conceptuales a estructuras físicas con "
    "claves primarias, claves foráneas y restricciones de integridad referencial. La carga de datos confirmó "
    "que el orden de inserción está determinado por las dependencias entre tablas, ya que el motor rechaza "
    "cualquier referencia a una fila inexistente.")
d.p("El análisis de los planes de ejecución demuestra que un índice solo mejora una consulta cuando coincide "
    "con el criterio de búsqueda u ordenamiento a partir de su primera columna, y que una vista no garantiza "
    "el orden de los resultados cuando la consulta externa define uno propio. Estos criterios resultan "
    "necesarios para diseñar índices que acompañen las consultas reales de un sistema.")

# Unimos cada título con el bloque siguiente para evitar títulos huérfanos
from reportlab.platypus import Paragraph as _P
merged, i = [], 0
while i < len(d.f):
    fl = d.f[i]
    group = [fl]
    while isinstance(fl, _P) and fl.style.name in ("h1", "sub", "consigna") and i + 1 < len(d.f):
        i += 1
        fl = d.f[i]
        group.append(fl)
    merged.append(KeepTogether(group) if len(group) > 1 else group[0])
    i += 1
d.f[:] = merged
d.build()
print("OK", OUT)
