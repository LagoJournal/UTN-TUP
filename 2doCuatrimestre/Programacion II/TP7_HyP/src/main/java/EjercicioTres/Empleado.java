package EjercicioTres;

// Clase abstracta base de todos los empleados
public abstract class Empleado {

    protected String nombre;
    protected double sueldoBase;

    public Empleado(String nombre, double sueldoBase) {
        this.nombre = nombre;
        this.sueldoBase = sueldoBase;
    }

    public String getNombre() {
        return nombre;
    }

    // Cada tipo de empleado calcula su sueldo de forma distinta
    public abstract double calcularSueldo();
}
