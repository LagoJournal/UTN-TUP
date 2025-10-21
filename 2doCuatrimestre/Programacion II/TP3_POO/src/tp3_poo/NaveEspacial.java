/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp3_poo;

/**
 *
 * @author agustinlago
 */
public class NaveEspacial {
    private final String nombre;
    private double combustible;
    private final double MAX_COMBUSTIBLE;

    public NaveEspacial(String nombre, double combustibleInicial, double capacidadMaxima) {
        this.nombre = nombre;
        this.MAX_COMBUSTIBLE = capacidadMaxima;
        this.combustible = Math.min(combustibleInicial, MAX_COMBUSTIBLE);
    }

    public void despegar() {
        double consumo = 10;
        if (combustible >= consumo) {
            combustible -= consumo;
            System.out.println(nombre + " despegó. Combustible restante: " + combustible);
        } else {
            System.out.println(nombre + " no tiene suficiente combustible.");
        }
    }

    public void avanzar(double distancia) {
        double consumo = 0.5 * distancia;
        if (combustible >= consumo) {
            combustible -= consumo;
            System.out.println(nombre + " recorrio " + distancia + " km. Combustible restante: " + combustible);
        } else {
            System.out.println(nombre + " no tiene combustible para avanzar " + distancia + " km.");
        }
    }

    public void recargarCombustible(double cantidad) {
        if (cantidad <= 0) {
            System.out.println("Cantidad de combustible inválida.");
            return;
        }
        combustible = Math.min(combustible + cantidad, MAX_COMBUSTIBLE);
        System.out.println("Recargado. Combustible actual: " + combustible);
    }

    public void mostrarEstado() {
        System.out.println("Nave " + nombre + " - Combustible: " + combustible + "/" + MAX_COMBUSTIBLE);
    }
}
