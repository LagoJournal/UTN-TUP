package Ejercicio_02;

/**
 * Prueba la clase Mascota: validación de edad negativa y simulación
 * del paso del tiempo con cumplirAnios().
 *
 * @author agustinlago
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 2: Registro de Mascotas ===\n");

        Mascota mascota = new Mascota("Bobby", "Perro", 3);

        System.out.println("--- Probando validación con edad negativa ---");
        mascota.setEdad(-5);

        System.out.println("\n--- Cargando datos válidos ---");
        mascota.setNombre("Bobby");
        mascota.setEspecie("Perro");
        mascota.setEdad(3);

        System.out.println("\n--- Información inicial ---");
        mascota.mostrarInfo();

        System.out.println("\n--- Simulando el paso del tiempo ---");
        mascota.cumplirAnios();
        mascota.cumplirAnios();

        System.out.println("\n--- Información final ---");
        mascota.mostrarInfo();
    }
}
