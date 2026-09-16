package Ejercicio_03;

import java.time.Year;

/**
 * Representa un libro con título, autor y año de publicación.
 * El año se valida contra el año actual del sistema.
 *
 * @author agustinlago
 */
public class Libro {
    private String titulo;
    private String autor;
    private int anioPublicacion;

    public Libro(String titulo, String autor, int anioPublicacion) {
        setTitulo(titulo);
        setAutor(autor);
        setAnioPublicacion(anioPublicacion);
    }

    // ---- Getters ----
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnioPublicacion() { return anioPublicacion; }

    // ---- Setters con validación ----
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            System.out.println("Error: el título no puede estar vacío.");
            return;
        }
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        if (autor == null || autor.trim().isEmpty()) {
            System.out.println("Error: el autor no puede estar vacío.");
            return;
        }
        this.autor = autor;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        int anioActual = Year.now().getValue();
        if (anioPublicacion <= 0 || anioPublicacion > anioActual) {
            System.out.println("Error: año de publicación inválido: " + anioPublicacion
                    + ". Debe ser mayor a 0 y no puede ser un año futuro.");
            return;
        }
        this.anioPublicacion = anioPublicacion;
    }

    // ---- Comportamiento ----
    public void mostrarInfo() {
        System.out.println("Libro: " + titulo + " - " + autor + " (" + anioPublicacion + ")");
    }
}
