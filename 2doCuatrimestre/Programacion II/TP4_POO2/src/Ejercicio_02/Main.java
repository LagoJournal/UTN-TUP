package Ejercicio_02;

/**
 * Prueba la clase Libro: creación con y sin editorial explícita, las dos
 * variantes de actualizarTitulo (incluyendo título vacío ignorado), y el
 * efecto del cambio de editorial estática sobre todos los libros.
 *
 * @author agustinlago
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Caso 2: Registro de Libros con Editorial ===\n");

        Libro libro1 = new Libro("Clean Code", "Robert C. Martin");
        Libro libro2 = new Libro("Effective Java", "Joshua Bloch", "O'Reilly");

        System.out.println("--- Estado inicial ---");
        System.out.println(libro1);
        System.out.println(libro2);

        System.out.println("\n--- Probando actualizarTitulo ---");
        libro1.actualizarTitulo("Clean Code (2da edición)");
        libro2.actualizarTitulo("Edición", "Effective Java 3ra");

        System.out.println("\n--- Probando título vacío (debe ignorarse) ---");
        libro1.actualizarTitulo("   ");

        System.out.println("\n--- Estado tras las actualizaciones ---");
        System.out.println(libro1);
        System.out.println(libro2);

        System.out.println("\n--- Cambiando la editorial global ---");
        Libro.cambiarEditorial("Pearson");
        System.out.println(libro1);
        System.out.println(libro2);
    }
}
