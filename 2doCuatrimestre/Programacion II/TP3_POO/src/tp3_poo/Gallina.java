/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp3_poo;

/**
 *
 * @author agustinlago
 */
public class Gallina {
    private final int id;
    private int edad;
    private int huevos;

    public Gallina(int idGallina, int edad) {
        this.id = idGallina;
        this.edad = edad;
        this.huevos = 0;
    }

    public void ponerHuevo() {
        huevos++;
    }

    public void envejecer() {
        edad++;
    }

    public void mostrarEstado() {
        System.out.println("Gallina " + id + " (" + edad + ") puso " + huevos + " huevos." );
    }
}
