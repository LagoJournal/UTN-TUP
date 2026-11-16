# TP 7 – Herencia y Polimorfismo en Java

Programación II – Tecnicatura Universitaria en Programación a Distancia (UTN)
Autor: Agustín Lago – lagoagustindev@gmail.com

## Estructura

```
src/main/java/
 ┣ App.java                 → ejecuta las 4 demos
 ┣ EjercicioUno/            → Vehiculo, Auto, Demo
 ┣ EjercicioDos/            → Figura (abstracta), Circulo, Rectangulo, Demo
 ┣ EjercicioTres/           → Empleado (abstracta), EmpleadoPlanta, EmpleadoTemporal, Demo
 ┗ EjercicioCuatro/         → Animal, Perro, Gato, Vaca, Demo
```

## Requisitos

- JDK 17 o superior
- Maven (opcional)

## Compilar y ejecutar con Maven

```bash
mvn compile
mvn exec:java                                   # las 4 demos (App)
mvn exec:java -Dexec.mainClass=EjercicioUno.Demo
mvn exec:java -Dexec.mainClass=EjercicioDos.Demo
mvn exec:java -Dexec.mainClass=EjercicioTres.Demo
mvn exec:java -Dexec.mainClass=EjercicioCuatro.Demo
```

## Compilar y ejecutar sin Maven

Desde la raíz del proyecto:

```bash
javac -encoding UTF-8 -d out src/main/java/App.java src/main/java/Ejercicio*/*.java
java -cp out App                    # las 4 demos
java -cp out EjercicioUno.Demo
java -cp out EjercicioDos.Demo
java -cp out EjercicioTres.Demo
java -cp out EjercicioCuatro.Demo
```

También se puede abrir la carpeta en IntelliJ / NetBeans (proyecto Maven) y ejecutar `App` o cualquier `Demo`.

## Conceptos aplicados

- **Ejercicio 1:** `extends`, atributos `protected`, `super(marca, modelo)` en el constructor, `mostrarInfo()` sobrescrito y upcasting.
- **Ejercicio 2:** clase y método abstractos (`Figura.calcularArea()`), array de `Figura` recorrido con polimorfismo.
- **Ejercicio 3:** `calcularSueldo()` polimórfico, clasificación con `instanceof` y downcasting seguro para invocar `getAntiguedad()`.
- **Ejercicio 4:** `hacerSonido()` sobrescrito con `@Override` en `Perro`, `Gato` y `Vaca`, recorrido de una lista de `Animal`.
