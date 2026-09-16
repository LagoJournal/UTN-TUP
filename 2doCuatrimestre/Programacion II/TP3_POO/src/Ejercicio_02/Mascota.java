package Ejercicio_02;

/**
 * Representa una mascota con nombre, especie y edad.
 * Los atributos son privados y se protegen mediante validaciones
 * en los setters.
 *
 * @author agustinlago
 */
public class Mascota {
    private String nombre;
    private String especie;
    private int edad;

    public Mascota(String nombre, String especie, int edad) {
        setNombre(nombre);
        setEspecie(especie);
        setEdad(edad);
    }

    // ---- Getters ----
    public String getNombre() { return nombre; }
    public String getEspecie() { return especie; }
    public int getEdad() { return edad; }

    // ---- Setters con validación ----
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: el nombre no puede estar vacío.");
            return;
        }
        this.nombre = nombre;
    }

    public void setEspecie(String especie) {
        if (especie == null || especie.trim().isEmpty()) {
            System.out.println("Error: la especie no puede estar vacía.");
            return;
        }
        this.especie = especie;
    }

    public void setEdad(int edad) {
        if (edad < 0) {
            System.out.println("Error: la edad no puede ser negativa (recibido: " + edad + ").");
            return;
        }
        this.edad = edad;
    }

    // ---- Comportamiento ----
    public void mostrarInfo() {
        System.out.println(nombre + " (" + especie + "): " + edad + " años.");
    }

    public void cumplirAnios() {
        edad++;
        System.out.println(nombre + " cumplió " + edad + " años!");
    }
}
