package Ejercicio_04;

/**
 * Representa una gallina en la granja digital, con identificador,
 * edad y cantidad de huevos puestos. Cada instancia mantiene su
 * propio estado de forma independiente.
 *
 * @author agustinlago
 */
public class Gallina {
    private int idGallina;
    private int edad;
    private int huevosPuestos;

    public Gallina(int idGallina, int edad) {
        this.idGallina = idGallina;
        setEdad(edad);
        this.huevosPuestos = 0;
    }

    // ---- Getters ----
    public int getIdGallina() { return idGallina; }
    public int getEdad() { return edad; }
    public int getHuevosPuestos() { return huevosPuestos; }

    // ---- Setters con validación ----
    public void setIdGallina(int idGallina) {
        this.idGallina = idGallina;
    }

    public void setEdad(int edad) {
        if (edad < 0) {
            System.out.println("Error: la edad de la gallina " + idGallina + " no puede ser negativa (recibido: " + edad + ").");
            return;
        }
        this.edad = edad;
    }

    public void setHuevosPuestos(int huevosPuestos) {
        if (huevosPuestos < 0) {
            System.out.println("Error: la cantidad de huevos no puede ser negativa (recibido: " + huevosPuestos + ").");
            return;
        }
        this.huevosPuestos = huevosPuestos;
    }

    // ---- Comportamiento ----
    public void ponerHuevo() {
        huevosPuestos++;
    }

    public void envejecer() {
        edad++;
    }

    public void mostrarEstado() {
        System.out.println("Gallina #" + idGallina + " - Edad: " + edad + " - Huevos puestos: " + huevosPuestos);
    }
}
