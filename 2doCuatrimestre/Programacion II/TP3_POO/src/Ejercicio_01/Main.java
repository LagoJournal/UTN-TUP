package Ejercicio_01;

/**
 * Prueba la clase Estudiante: validaciones con datos inválidos,
 * carga de datos correctos y manejo de límites de calificación.
 *
 * @author agustinlago
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 1: Registro de Estudiantes ===\n");

        Estudiante estudiante = new Estudiante("Juan", "Pérez", "Programación II", 7.5);

        System.out.println("--- Probando validaciones con datos inválidos ---");
        estudiante.setCalificacion(15.0);
        estudiante.setNombre("");
        estudiante.setCurso("   ");

        System.out.println("\n--- Cargando datos correctos ---");
        estudiante.setNombre("Agustín");
        estudiante.setApellido("Lago");
        estudiante.setCurso("Programación II");
        estudiante.setCalificacion(8.5);
        estudiante.mostrarInfo();

        System.out.println("\n--- Probando subir y bajar calificación (incluyendo límites) ---");
        estudiante.subirCalificacion(1.0);
        estudiante.mostrarInfo();

        estudiante.subirCalificacion(5.0); // supera 10.0, debe rechazarse
        estudiante.mostrarInfo();

        estudiante.bajarCalificacion(3.0);
        estudiante.mostrarInfo();

        estudiante.bajarCalificacion(10.0); // bajaría de 0.0, debe rechazarse
        estudiante.mostrarInfo();
    }
}
