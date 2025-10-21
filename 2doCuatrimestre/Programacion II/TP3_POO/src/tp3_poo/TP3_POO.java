/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tp3_poo;

/**
 *
 * @author agustinlago
 */
public class TP3_POO {

    public static void main(String[] args) {
        
        // Ejercicio 1
        Estudiante estudiante = new Estudiante("Agustín", "Lago", "Programación II", 8.5);
        estudiante.mostrarInfo();
        estudiante.subirCalificacion(1.2);
        estudiante.bajarCalificacion(2.0);
        estudiante.mostrarInfo();
        
        
        // Ejercicio 2
        Mascota mascota = new Mascota("Bobby", "Perro", 3);
        mascota.mostrarInfo();
        mascota.cumplirAnios();
        mascota.mostrarInfo();
        
        
        // Ejercicio 3
        Libro libro = new Libro("Apuntes Programacion II", "Agustín", 2030);
        libro.mostrarInfo();
        libro.setAnioPublicacion(2019);
        libro.mostrarInfo();
        
        
        // Ejercicio 4
        Gallina gallina1 = new Gallina(1, 2);
        Gallina gallina2 = new Gallina(2, 1);

        gallina1.ponerHuevo();
        gallina2.ponerHuevo();
        gallina2.ponerHuevo();
        gallina1.envejecer();

        gallina1.mostrarEstado();
        gallina2.mostrarEstado();
        
        
        // Ejercicio 5
        
        NaveEspacial nave = new NaveEspacial("La maleducada", 50, 100);
        nave.mostrarEstado();
        nave.despegar();
        nave.avanzar(20);
        nave.recargarCombustible(30);
        nave.avanzar(50);
        nave.mostrarEstado();
        
        
        
    }
    
}
