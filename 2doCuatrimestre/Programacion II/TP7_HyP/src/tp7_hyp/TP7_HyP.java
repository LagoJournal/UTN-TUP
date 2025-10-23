/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tp7_hyp;

import java.util.ArrayList;
/**
 *
 * @author agustinlago
 */
public class TP7_HyP {
 
    public static void main(String[] args) {
        
       // Ejercicio 1
        Auto auto = new Auto("Toyota", "Corolla", 4);
        auto.mostrarInfo();
        
        
       // Ejercicio 2
        Figura[] figuras = {
            new Circulo(3.5),
            new Rectangulo(4, 2)
        };
        System.out.println("\nAreas calculadas:");
        for (Figura f : figuras) {
            System.out.println(f.getNombre() + ": " + f.calcularArea());
        }
                
        
        // Ejercico 3
        ArrayList<Empleado> empleados = new ArrayList<>();
        empleados.add(new EmpleadoPlanta("Agustín", 300000));
        empleados.add(new EmpleadoTemporal("Luis", 20, 10000));
        System.out.println("\nSueldos:");
        for (Empleado e : empleados) {
            System.out.println(e.nombre + " $" + e.calcularSueldo());
            if (e instanceof EmpleadoPlanta) {
                System.out.println(" [Empleado de planta]");
            } else if (e instanceof EmpleadoTemporal) {
                System.out.println(" [Empleado temporal]");
            }
        }
        
        
        // Ejercico 4
        ArrayList<Animal> animales = new ArrayList<>();
        animales.add(new Perro("Toby"));
        animales.add(new Gato("Michi"));
        animales.add(new Vaca("Lola"));
        System.out.println("\nAnimales:");
        for (Animal a : animales) {
            a.describirAnimal();
            a.hacerSonido();
        }
    }
    
}
