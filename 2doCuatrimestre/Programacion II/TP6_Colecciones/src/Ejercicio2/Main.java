package Ejercicio2;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 1. Creamos la biblioteca
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central");
        System.out.println("=== 1. Biblioteca: " + biblioteca.getNombre() + " ===");

        // 2. Creamos tres autores
        Autor borges = new Autor("A01", "Jorge Luis Borges", "Argentina");
        Autor cortazar = new Autor("A02", "Julio Cortázar", "Argentina");
        Autor garciaMarquez = new Autor("A03", "Gabriel García Márquez", "Colombia");

        // 3. Agregamos cinco libros; la biblioteca los instancia internamente
        biblioteca.agregarLibro("978-1", "Ficciones", 1944, borges);
        biblioteca.agregarLibro("978-2", "El Aleph", 1949, borges);
        biblioteca.agregarLibro("978-3", "Rayuela", 1963, cortazar);
        biblioteca.agregarLibro("978-4", "Bestiario", 1951, cortazar);
        biblioteca.agregarLibro("978-5", "Cien años de soledad", 1967, garciaMarquez);

        // 4. Listado con información del autor
        System.out.println("\n=== 4. Libros ===");
        biblioteca.listarLibros();

        // 5. Búsqueda por ISBN (válida e inválida)
        System.out.println("\n=== 5. Búsqueda por ISBN ===");
        mostrarBusqueda(biblioteca, "978-3");
        mostrarBusqueda(biblioteca, "000-0");

        // 6. Filtro por año
        System.out.println("\n=== 6. Libros publicados en 1949 ===");
        List<Libro> de1949 = biblioteca.filtrarLibrosPorAnio(1949);
        if (de1949.isEmpty()) {
            System.out.println("Sin resultados.");
        }
        for (Libro l : de1949) {
            l.mostrarInfo();
        }

        // 7. Eliminación y listado restante
        System.out.println("\n=== 7. Eliminar ISBN 978-4 ===");
        System.out.println(biblioteca.eliminarLibro("978-4") ? "Libro eliminado." : "No se encontró el libro.");
        System.out.println(biblioteca.eliminarLibro("000-0") ? "Libro eliminado." : "ISBN 000-0 no encontrado.");
        biblioteca.listarLibros();

        // 8. Cantidad total
        System.out.println("\n=== 8. Cantidad de libros ===");
        System.out.println("Total: " + biblioteca.obtenerCantidadLibros());

        // 9. Autores presentes en la biblioteca
        System.out.println("\n=== 9. Autores disponibles ===");
        biblioteca.mostrarAutoresDisponibles();
    }

    private static void mostrarBusqueda(Biblioteca biblioteca, String isbn) {
        Libro l = biblioteca.buscarLibroPorIsbn(isbn);
        if (l != null) {
            l.mostrarInfo();
        } else {
            System.out.println("No existe un libro con ISBN " + isbn + ".");
        }
    }
}
