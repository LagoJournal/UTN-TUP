package Ejercicio_03;

/**
 * Prueba la clase Libro: rechazo de un año inválido y posterior
 * corrección con un año válido.
 *
 * @author agustinlago
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 3: Encapsulamiento con la Clase Libro ===\n");

        Libro libro = new Libro("Apuntes de Programación II", "Agustín Lago", 2023);
        libro.mostrarInfo();

        System.out.println("\n--- Intentando modificar el año con un valor inválido ---");
        libro.setAnioPublicacion(2999);
        libro.mostrarInfo();

        System.out.println("\n--- Modificando el año con un valor válido ---");
        libro.setAnioPublicacion(2024);
        libro.mostrarInfo();
    }
}
