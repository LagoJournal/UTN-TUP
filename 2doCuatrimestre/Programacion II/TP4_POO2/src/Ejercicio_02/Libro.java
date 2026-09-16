package Ejercicio_02;

/**
 * Representa un libro con título, autor y una editorial COMPARTIDA por
 * todos los libros (atributo estático). La editorial se modifica sólo a
 * través del método estático cambiarEditorial (no hay setter de instancia
 * para ella).
 *
 * @author agustinlago
 */
public class Libro {
    private String titulo;
    private String autor;

    private static String editorial = "Independiente";

    // Constructor principal: título y autor (usa la editorial global actual).
    public Libro(String titulo, String autor) {
        setTitulo(titulo);
        setAutor(autor);
    }

    // Constructor parcial (DRY): además fija una editorial inicial para todos los libros.
    public Libro(String titulo, String autor, String editorialInicial) {
        this(titulo, autor);
        cambiarEditorial(editorialInicial);
    }

    // ---- Getters ----
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public static String getEditorial() { return editorial; }

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

    // Único punto de modificación de la editorial global (no hay setEditorial()).
    public static void cambiarEditorial(String nueva) {
        if (nueva == null || nueva.trim().isEmpty()) {
            System.out.println("Error: la editorial no puede estar vacía.");
            return;
        }
        editorial = nueva;
    }

    // ---- Métodos sobrecargados ----
    public void actualizarTitulo(String nuevoTitulo) {
        if (nuevoTitulo == null || nuevoTitulo.trim().isEmpty()) {
            System.out.println("Título vacío ignorado. Se mantiene: " + titulo);
            return;
        }
        setTitulo(nuevoTitulo);
    }

    public void actualizarTitulo(String prefijo, String nuevoTitulo) {
        if (nuevoTitulo == null || nuevoTitulo.trim().isEmpty()) {
            System.out.println("Título vacío ignorado. Se mantiene: " + titulo);
            return;
        }
        actualizarTitulo(prefijo + " " + nuevoTitulo);
    }

    @Override
    public String toString() {
        return "\"" + titulo + "\" - " + autor + " (Editorial: " + editorial + ")";
    }
}
