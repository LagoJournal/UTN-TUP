/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp4_poo2;

/**
 *
 * @author agustinlago
 * 
 * 
 */
public class Empleado {
    private int id;
    private String nombre;
    private String puesto;
    private double salario;

    private static int totalEmpleados = 0;
    private static int contadorId = 1; 

    
    public Empleado(int id, String nombre, String puesto, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        totalEmpleados++;
        // Necesario para que no haya repeticion de ids
        if (id >= contadorId) contadorId = id + 1;
    }

    public Empleado(String nombre, String puesto) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = 2000.0;
        totalEmpleados++;
    }

    public void actualizarSalario(double porcentajeAumento) {
        this.salario += this.salario * (porcentajeAumento / 100);
    }

    public void actualizarSalario(int aumentoFijo) {
        this.salario += aumentoFijo;
    }

    // Método toString()
    @Override
    public String toString() {
        return "["+ id + "]:" + nombre + " - " + puesto + " ($" + salario + ")"; 
    }

    public static int mostrarTotalEmpleados() {
        return totalEmpleados;
    }

    // Getters y Setters
    public int getId() { return id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPuesto() { return puesto; }

    public void setPuesto(String puesto) { this.puesto = puesto; }

    public double getSalario() { return salario; }

    public void setSalario(double salario) { this.salario = salario; }
}
