/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp3_poo;

/**
 *
 * @author agustinlago
 */
public class Mascota {
    private final String nombre;
    private final String especie;
    private int edad;

    public Mascota(String nombre, String especie, int edad) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
    }

    public void mostrarInfo() {
        System.out.println(nombre + " (" + especie + "): " + edad + "años.");
    }

    public void cumplirAnios() {
        edad++;
        System.out.println(nombre + " cumplió " + edad + " años!");
    }
}
