package Ejercicio_04;

/**
 * Prueba la clase Gallina: validación de edad negativa y demostración
 * de que cada instancia mantiene su propio estado independiente.
 *
 * @author agustinlago
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 4: Gestión de Gallinas en Granja Digital ===\n");

        Gallina gallinaA = new Gallina(1, 2);
        Gallina gallinaB = new Gallina(2, 1);

        System.out.println("--- Probando validación con edad negativa ---");
        gallinaA.setEdad(-3);

        System.out.println("\n--- Simulando acciones de forma independiente ---");
        gallinaA.ponerHuevo();
        gallinaA.ponerHuevo();
        gallinaB.ponerHuevo();
        gallinaA.envejecer();

        System.out.println("\n--- Estado final de ambas gallinas ---");
        gallinaA.mostrarEstado();
        gallinaB.mostrarEstado();
    }
}
