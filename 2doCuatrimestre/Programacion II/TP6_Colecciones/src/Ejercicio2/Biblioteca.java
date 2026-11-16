package Ejercicio2;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private String nombre;
    private List<Libro> libros;

    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.libros = new ArrayList<>();
    }

    // Composición: la Biblioteca crea el Libro, nadie más tiene la referencia
    public void agregarLibro(String isbn, String titulo, int anioPublicacion, Autor autor) {
        if (buscarLibroPorIsbn(isbn) == null) {
            libros.add(new Libro(isbn, titulo, anioPublicacion, autor));
        } else {
            System.out.println("Ya existe un libro con ISBN " + isbn + ".");
        }
    }

    public void listarLibros() {
        if (libros.isEmpty()) {
            System.out.println("La biblioteca no tiene libros.");
            return;
        }
        for (Libro l : libros) {
            l.mostrarInfo();
        }
    }

    public Libro buscarLibroPorIsbn(String isbn) {
        for (Libro l : libros) {
            if (l.getIsbn().equals(isbn)) {
                return l;
            }
        }
        return null;
    }

    public boolean eliminarLibro(String isbn) {
        Libro l = buscarLibroPorIsbn(isbn);
        if (l != null) {
            libros.remove(l);
            return true;
        }
        return false;
    }

    public int obtenerCantidadLibros() {
        return libros.size();
    }

    public List<Libro> filtrarLibrosPorAnio(int anio) {
        List<Libro> resultado = new ArrayList<>();
        for (Libro l : libros) {
            if (l.getAnioPublicacion() == anio) {
                resultado.add(l);
            }
        }
        return resultado;
    }

    // Juntamos los autores sin repetir, ya que un autor puede tener varios libros
    public void mostrarAutoresDisponibles() {
        List<Autor> autores = new ArrayList<>();
        for (Libro l : libros) {
            if (!autores.contains(l.getAutor())) {
                autores.add(l.getAutor());
            }
        }
        for (Autor a : autores) {
            a.mostrarInfo();
        }
    }

    public String getNombre() {
        return nombre;
    }
}
