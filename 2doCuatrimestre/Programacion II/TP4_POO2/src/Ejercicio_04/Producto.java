package Ejercicio_04;

/**
 * Representa un producto de inventario con un precio base y un IVA
 * COMPARTIDO por todos los productos (atributo estático), modificable solo
 * a través del método estático cambiarIVA.
 *
 * @author agustinlago
 */
public class Producto {
    private String nombre;
    private double precioBase;

    private static double IVA = 0.21;

    // Constructor principal.
    public Producto(String nombre, double precioBase) {
        setNombre(nombre);
        setPrecioBase(precioBase);
    }

    // Constructor parcial (DRY): precio por defecto, delega en el principal.
    public Producto(String nombre) {
        this(nombre, 100);
    }

    // ---- Getters ----
    public String getNombre() { return nombre; }
    public double getPrecioBase() { return precioBase; }
    public static double getIVA() { return IVA; }

    // ---- Setters con validación ----
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: el nombre no puede estar vacío.");
            return;
        }
        this.nombre = nombre;
    }

    public void setPrecioBase(double precioBase) {
        if (precioBase < 0) {
            System.out.println("Error: el precio base no puede ser negativo (recibido: " + precioBase + ").");
            return;
        }
        this.precioBase = precioBase;
    }

    // Único punto de modificación del IVA global (no hay setIVA()).
    public static void cambiarIVA(double nuevo) {
        if (nuevo < 0 || nuevo > 1) {
            System.out.println("Error: el IVA debe expresarse como fracción entre 0 y 1 (ej. 0.21).");
            return;
        }
        IVA = nuevo;
    }

    // ---- Métodos sobrecargados ----
    public double aplicarDescuento(double porcentaje) {
        double pct = porcentaje;
        if (pct < 0) {
            System.out.println("Aviso: porcentaje negativo, se interpreta como 0% de descuento.");
            pct = 0;
        } else if (pct > 100) {
            System.out.println("Aviso: porcentaje mayor a 100, se ajusta a 100%.");
            pct = 100;
        }
        return precioBase * (1 - pct / 100);
    }

    public double aplicarDescuento(double porcentaje, double precioMinimo) {
        double resultado = aplicarDescuento(porcentaje);
        return Math.max(resultado, precioMinimo);
    }

    // ---- Métodos fiscales ----
    public double calcularPrecioFinal() {
        return precioBase * (1 + IVA);
    }

    @Override
    public String toString() {
        return nombre + " - Precio base: $" + precioBase + " - Precio final (IVA incl.): $" + calcularPrecioFinal();
    }
}
