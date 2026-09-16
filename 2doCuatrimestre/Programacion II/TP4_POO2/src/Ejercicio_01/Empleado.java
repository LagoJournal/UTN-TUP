package Ejercicio_01;

/**
 * Representa a un empleado de la empresa. El id es inmutable y autogenerado
 * cuando no se especifica; totalEmpleados lleva la cuenta global de objetos
 * creados. Ninguno de los dos posee setter (identidad y estado global
 * protegidos).
 *
 * @author agustinlago
 */
public class Empleado {
    private final int id;
    private String nombre;
    private String puesto;
    private double salario;

    private static int totalEmpleados = 0;
    private static int contadorId = 1;

    // Constructor principal: recibe todos los atributos.
    public Empleado(int id, String nombre, String puesto, double salario) {
        this.id = id;
        setNombre(nombre);
        setPuesto(puesto);
        setSalario(salario);
        totalEmpleados++;
        if (id >= contadorId) {
            contadorId = id + 1;
        }
    }

    // Constructor parcial (DRY): ID automático y salario por defecto, delega en el principal.
    public Empleado(String nombre, String puesto) {
        this(contadorId, nombre, puesto, 2000.0);
    }

    // ---- Getters ----
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getPuesto() { return puesto; }
    public double getSalario() { return salario; }

    // ---- Setters con validación (sin setter para id ni para totalEmpleados) ----
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: el nombre no puede estar vacío.");
            return;
        }
        this.nombre = nombre;
    }

    public void setPuesto(String puesto) {
        if (puesto == null || puesto.trim().isEmpty()) {
            System.out.println("Error: el puesto no puede estar vacío.");
            return;
        }
        this.puesto = puesto;
    }

    public void setSalario(double salario) {
        if (salario < 0) {
            System.out.println("Error: el salario no puede ser negativo (recibido: " + salario + ").");
            return;
        }
        this.salario = salario;
    }

    // ---- Métodos sobrecargados ----
    public void actualizarSalario(double porcentajeAumento) {
        if (porcentajeAumento < 0) {
            System.out.println("Error: el porcentaje de aumento no puede ser negativo.");
            return;
        }
        salario += salario * (porcentajeAumento / 100);
        System.out.println("Salario de " + nombre + " actualizado (+" + porcentajeAumento + "%). Nuevo salario: " + salario);
    }

    public void actualizarSalario(int aumentoFijo) {
        if (aumentoFijo < 0) {
            System.out.println("Error: el aumento fijo no puede ser negativo.");
            return;
        }
        salario += aumentoFijo;
        System.out.println("Salario de " + nombre + " actualizado (+" + aumentoFijo + "). Nuevo salario: " + salario);
    }

    // ---- Diagnóstico ----
    public static int mostrarTotalEmpleados() {
        return totalEmpleados;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nombre + " - " + puesto + " ($" + salario + ")";
    }
}
