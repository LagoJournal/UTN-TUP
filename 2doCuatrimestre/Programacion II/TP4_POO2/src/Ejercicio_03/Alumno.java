package Ejercicio_03;

/**
 * Representa un alumno con nombre y promedio. La nota de aprobación es un
 * umbral COMPARTIDO por todos los alumnos (atributo estático), modificable
 * solo a través del método estático cambiarNotaAprobacion.
 *
 * @author agustinlago
 */
public class Alumno {
    private String nombre;
    private double promedio;

    private static double notaAprobacion = 6;

    // Constructor principal.
    public Alumno(String nombre, double promedio) {
        setNombre(nombre);
        setPromedio(promedio);
    }

    // Constructor parcial (DRY): promedio inicial en 0, delega en el principal.
    public Alumno(String nombre) {
        this(nombre, 0);
    }

    // ---- Getters ----
    public String getNombre() { return nombre; }
    public double getPromedio() { return promedio; }
    public static double getNotaAprobacion() { return notaAprobacion; }

    // ---- Setters con validación ----
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: el nombre no puede estar vacío.");
            return;
        }
        this.nombre = nombre;
    }

    // Programación defensiva: ajusta el promedio al límite más cercano si se sale de rango.
    public void setPromedio(double promedio) {
        if (promedio < 0) {
            System.out.println("Aviso: promedio " + promedio + " fuera de rango, se ajusta a 0.");
            this.promedio = 0;
        } else if (promedio > 10) {
            System.out.println("Aviso: promedio " + promedio + " fuera de rango, se ajusta a 10.");
            this.promedio = 10;
        } else {
            this.promedio = promedio;
        }
    }

    // Único punto de modificación de la nota de aprobación global (no hay setNotaAprobacion()).
    public static void cambiarNotaAprobacion(double nueva) {
        if (nueva < 0 || nueva > 10) {
            System.out.println("Error: la nota de aprobación debe estar entre 0 y 10.");
            return;
        }
        notaAprobacion = nueva;
    }

    // ---- Métodos sobrecargados ----
    public void actualizarPromedio(double nuevoPromedio) {
        setPromedio(nuevoPromedio);
    }

    public void actualizarPromedio(double[] notas) {
        if (notas == null || notas.length == 0) {
            System.out.println("Error: no se proporcionaron notas para calcular el promedio.");
            return;
        }
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        actualizarPromedio(suma / notas.length);
    }

    // ---- Método de negocio ----
    public boolean aprobo() {
        return promedio >= notaAprobacion;
    }

    @Override
    public String toString() {
        return nombre + " - Promedio: " + promedio + " - " + (aprobo() ? "Aprobó" : "No aprobó");
    }
}
