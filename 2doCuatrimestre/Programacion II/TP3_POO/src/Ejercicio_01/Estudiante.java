package Ejercicio_01;

/**
 * Representa a un estudiante con nombre, apellido, curso y calificación.
 * Aplica encapsulamiento: los atributos son privados y se accede/modifica
 * a través de getters y setters con validación.
 *
 * @author agustinlago
 */
public class Estudiante {
    private String nombre;
    private String apellido;
    private String curso;
    private double calificacion;

    public Estudiante(String nombre, String apellido, String curso, double calificacion) {
        setNombre(nombre);
        setApellido(apellido);
        setCurso(curso);
        setCalificacion(calificacion);
    }

    // ---- Getters ----
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getCurso() { return curso; }
    public double getCalificacion() { return calificacion; }

    // ---- Setters con validación ----
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: el nombre no puede estar vacío.");
            return;
        }
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            System.out.println("Error: el apellido no puede estar vacío.");
            return;
        }
        this.apellido = apellido;
    }

    public void setCurso(String curso) {
        if (curso == null || curso.trim().isEmpty()) {
            System.out.println("Error: el curso no puede estar vacío.");
            return;
        }
        this.curso = curso;
    }

    public void setCalificacion(double calificacion) {
        if (calificacion < 0.0 || calificacion > 10.0) {
            System.out.println("Error: la calificación debe estar entre 0.0 y 10.0 (recibido: " + calificacion + ").");
            return;
        }
        this.calificacion = calificacion;
    }

    // ---- Comportamiento ----
    public void mostrarInfo() {
        System.out.println("Estudiante: " + nombre + " " + apellido
                + " - Curso: " + curso + " - Calificación: " + calificacion);
    }

    public void subirCalificacion(double puntos) {
        double nueva = calificacion + puntos;
        if (nueva > 10.0) {
            System.out.println("Error: no se puede subir la calificación por encima de 10.0 (resultaría en " + nueva + ").");
            return;
        }
        calificacion = nueva;
        System.out.println("Calificación aumentada en " + puntos + ". Nueva calificación: " + calificacion);
    }

    public void bajarCalificacion(double puntos) {
        double nueva = calificacion - puntos;
        if (nueva < 0.0) {
            System.out.println("Error: no se puede bajar la calificación por debajo de 0.0 (resultaría en " + nueva + ").");
            return;
        }
        calificacion = nueva;
        System.out.println("Calificación disminuida en " + puntos + ". Nueva calificación: " + calificacion);
    }
}
