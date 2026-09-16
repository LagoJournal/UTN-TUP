package Ejercicio_03;

/**
 * Prueba la clase Alumno: ambos constructores, las dos variantes de
 * actualizarPromedio (incluyendo valores fuera de rango), la condición
 * aprobo() y el impacto de cambiar la nota de aprobación global.
 *
 * @author agustinlago
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Caso 3: Sistema de Alumnos con Nota de Aprobación ===\n");

        Alumno alumno1 = new Alumno("Agustín", 7.5);
        Alumno alumno2 = new Alumno("Bruno");

        System.out.println("--- Actualizando promedios ---");
        alumno1.actualizarPromedio(8.5);
        alumno2.actualizarPromedio(new double[]{6.0, 7.0, 5.5});

        System.out.println("\n--- Probando robustez con valores fuera de rango ---");
        alumno1.actualizarPromedio(15.0);  // debe ajustarse a 10
        alumno2.actualizarPromedio(-3.0);  // debe ajustarse a 0

        System.out.println("\n--- Resultados ---");
        System.out.println(alumno1);
        System.out.println(alumno2);

        System.out.println("\n--- Cambiando la nota de aprobación global a 9 ---");
        Alumno.cambiarNotaAprobacion(9);
        System.out.println(alumno1);
        System.out.println(alumno2);
    }
}
