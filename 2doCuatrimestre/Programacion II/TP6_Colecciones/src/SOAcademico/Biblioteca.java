/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SOAcademico;

/**
 *
 * @author agustinlago
 */
import java.util.ArrayList;

public class Biblioteca {
    private String nombre;
    private ArrayList<Libro> libros;

    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.libros = new ArrayList<>();
    }

    public void agregarLibro(String isbn, String titulo, int anioPublicacion, Autor autor) {
        libros.add(new Libro(isbn, titulo, anioPublicacion, autor));
    }

    public void listarLibros() {
        System.out.println("Libros en \"" + nombre + "\":");
        for (Libro libro : libros) {
            libro.mostrarInfo();
        }
    }

    public Libro buscarLibroPorIsbn(String isbn) {
        for (Libro libro : libros)
            if (libro.getIsbn().equalsIgnoreCase(isbn))
                return libro;
        return null;
    }

    public void eliminarLibro(String isbn) {
        libros.removeIf(libro -> libro.getIsbn().equalsIgnoreCase(isbn));
    }

    public int obtenerCantidadLibros() {
        return libros.size();
    }

    public void filtrarLibrosPorAnio(int anio) {
        System.out.println("Libros publicados en " + anio + ":");
        for (Libro libro : libros)
            if (libro.getAnioPublicacion() == anio)
                libro.mostrarInfo();
    }

    public void mostrarAutoresDisponibles() {
        System.out.println("Autores disponibles:");
        libros.stream()
              .map(Libro::getAutor)
              .distinct()
              .forEach(System.out::println);
    }
}
