# ----------------------------------------------------
# SEMANA 2 - Practica: Tablas de frecuencia
# Dataset: Plataforma_Trabajo (nominal), Tickets_Soporte (discreta),
#          Tiempo_Conexion (continua)
# ----------------------------------------------------

if (!require(readxl)) install.packages("readxl")
library(readxl)

archivo <- file.choose() # elegir "Datos Practica Tabla de Frecuencias en R (V Nominal).xlsx"
datos <- read_excel(archivo)

# 1) Plataforma_Trabajo - NOMINAL -> solo Frec y Frec_Rel (sin acumulada)
tabla_plat <- table(datos$Plataforma_Trabajo)
f_rel_plat <- prop.table(tabla_plat)

tabla_plataforma <- data.frame(
  Plataforma = names(tabla_plat),
  Frec = as.vector(tabla_plat),
  Frec_Rel = round(as.vector(f_rel_plat), 3)
)
print(tabla_plataforma, row.names = FALSE)

# 2) Tickets_Soporte - DISCRETA -> Frec, Frec_Acum, Frec_Rel, Frec_Rel_Acum
tabla_tick <- table(datos$Tickets_Soporte)
f_acum_tick <- cumsum(tabla_tick)
f_rel_tick <- prop.table(tabla_tick)
f_rel_acum_tick <- cumsum(f_rel_tick)

tabla_tickets <- data.frame(
  Tickets = names(tabla_tick),
  Frec = as.vector(tabla_tick),
  Frec_Acum = as.vector(f_acum_tick),
  Frec_Rel = round(as.vector(f_rel_tick), 3),
  Frec_Rel_Acum = round(as.vector(f_rel_acum_tick), 3)
)
print(tabla_tickets, row.names = FALSE)

# 3) Tiempo_Conexion - CONTINUA -> agrupar con cut() (regla de Sturges)
n <- length(datos$Tiempo_Conexion)
k <- ceiling(1 + 3.322 * log10(n))
rango <- range(datos$Tiempo_Conexion)
amplitud <- ceiling((rango[2] - rango[1]) / k)
breaks <- seq(floor(rango[1]), ceiling(rango[2]) + amplitud, by = amplitud)
clases <- cut(datos$Tiempo_Conexion, breaks = breaks, right = FALSE)

tabla_tc <- table(clases)
f_acum_tc <- cumsum(tabla_tc)
f_rel_tc <- prop.table(tabla_tc)
f_rel_acum_tc <- cumsum(f_rel_tc)

tabla_tiempo_conexion <- data.frame(
  Intervalo = levels(clases),
  Frec = as.vector(tabla_tc),
  Frec_Acum = as.vector(f_acum_tc),
  Frec_Rel = round(as.vector(f_rel_tc), 3),
  Frec_Rel_Acum = round(as.vector(f_rel_acum_tc), 3)
)
print(tabla_tiempo_conexion, row.names = FALSE)
