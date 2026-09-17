# ----------------------------------------------------
# SEMANA 3 - Practica: Estadisticos descriptivos
# Dataset: V Nominal (Plataforma_Trabajo, Tickets_Soporte, Tiempo_Conexion)
# Variables a analizar: Tickets_Soporte, Tiempo_Conexion
# ----------------------------------------------------

if (!require(readxl)) install.packages("readxl")
library(readxl)

archivo <- file.choose() # elegir "Datos Practica Tabla de Frecuencias en R (V Nominal).xlsx"
datos <- read_excel(archivo)

# Funcion moda (R no trae una nativa)
moda <- function(x) {
  frecuencias <- table(x)
  as.numeric(names(frecuencias)[frecuencias == max(frecuencias)])
}

# --- Tickets_Soporte ---
media_tick <- mean(datos$Tickets_Soporte)
mediana_tick <- median(datos$Tickets_Soporte)
moda_tick <- moda(datos$Tickets_Soporte)
cuartiles_tick <- quantile(datos$Tickets_Soporte, probs = c(0.25, 0.5, 0.75))
sd_tick <- sd(datos$Tickets_Soporte)

cat("--- Tickets_Soporte ---\n")
cat("Media:", round(media_tick, 3), "\n")
cat("Mediana:", mediana_tick, "\n")
cat("Moda:", moda_tick, "\n")
print(cuartiles_tick)
cat("Desvio estandar:", round(sd_tick, 3), "\n\n")

# --- Tiempo_Conexion ---
media_tc <- mean(datos$Tiempo_Conexion)
mediana_tc <- median(datos$Tiempo_Conexion)
moda_tc <- moda(datos$Tiempo_Conexion)
cuartiles_tc <- quantile(datos$Tiempo_Conexion, probs = c(0.25, 0.5, 0.75))
sd_tc <- sd(datos$Tiempo_Conexion)

cat("--- Tiempo_Conexion ---\n")
cat("Media:", round(media_tc, 3), "\n")
cat("Mediana:", mediana_tc, "\n")
cat("Moda:", moda_tc, "\n")
print(cuartiles_tc)
cat("Desvio estandar:", round(sd_tc, 3), "\n")
