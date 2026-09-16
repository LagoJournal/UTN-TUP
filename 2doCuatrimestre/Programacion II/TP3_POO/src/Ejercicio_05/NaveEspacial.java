package Ejercicio_05;

/**
 * Representa una nave espacial con nombre y nivel de combustible,
 * acotado por una capacidad máxima. Valida combustible suficiente
 * antes de despegar o avanzar, y evita superar el tope al recargar.
 *
 * @author agustinlago
 */
public class NaveEspacial {
    private static final double CAPACIDAD_MAXIMA = 100;
    private static final double COSTO_DESPEGUE = 5;
    private static final double COSTO_POR_KM = 2;

    private String nombre;
    private double combustible;

    public NaveEspacial(String nombre, double combustible) {
        setNombre(nombre);
        setCombustible(combustible);
    }

    // ---- Getters ----
    public String getNombre() { return nombre; }
    public double getCombustible() { return combustible; }
    public double getCapacidadMaxima() { return CAPACIDAD_MAXIMA; }

    // ---- Setters con validación ----
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: el nombre de la nave no puede estar vacío.");
            return;
        }
        this.nombre = nombre;
    }

    public void setCombustible(double combustible) {
        if (combustible < 0 || combustible > CAPACIDAD_MAXIMA) {
            System.out.println("Error: el combustible debe estar entre 0 y " + CAPACIDAD_MAXIMA + " (recibido: " + combustible + ").");
            return;
        }
        this.combustible = combustible;
    }

    // ---- Comportamiento ----
    public void despegar() {
        if (combustible < COSTO_DESPEGUE) {
            System.out.println(nombre + ": no hay combustible suficiente para despegar. Acción cancelada.");
            return;
        }
        combustible -= COSTO_DESPEGUE;
        System.out.println(nombre + " despegó. Combustible restante: " + combustible);
    }

    public void avanzar(double distancia) {
        double costo = distancia * COSTO_POR_KM;
        if (costo > combustible) {
            System.out.println(nombre + ": no hay combustible suficiente para avanzar " + distancia + " km. Acción cancelada.");
            return;
        }
        combustible -= costo;
        System.out.println(nombre + " avanzó " + distancia + " km. Combustible restante: " + combustible);
    }

    public void recargarCombustible(double cantidad) {
        if (cantidad <= 0) {
            System.out.println("Error: la cantidad a recargar debe ser positiva.");
            return;
        }
        if (combustible + cantidad > CAPACIDAD_MAXIMA) {
            System.out.println(nombre + ": la recarga supera la capacidad máxima (" + CAPACIDAD_MAXIMA + "). Recarga cancelada.");
            return;
        }
        combustible += cantidad;
        System.out.println(nombre + " recargó combustible. Combustible actual: " + combustible);
    }

    public void mostrarEstado() {
        System.out.println("Nave " + nombre + " - Combustible: " + combustible + "/" + CAPACIDAD_MAXIMA);
    }
}
