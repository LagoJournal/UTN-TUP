package ejercicio3;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 3: Libro - Autor - Editorial ===\n");

        Autor autor = new Autor("Julio Cortázar", "Argentina");
        Editorial editorial = new Editorial("Sudamericana", "Humberto Primo 555, CABA");
        Libro libro = new Libro("Rayuela", "978-950-07-0321-9", autor, editorial);

        System.out.println(libro);

        System.out.println("\n--- Navegación unidireccional ---");
        System.out.println("Autor del libro: " + libro.getAutor().getNombre());

        System.out.println("\n--- Verificando agregación ---");
        System.out.println("La editorial existe por fuera del libro: " + editorial);
    }
}
