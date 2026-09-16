package Ejercicio_05;

/**
 * Prueba la clase NaveEspacial: fallo por combustible insuficiente al
 * avanzar, fallo por superar la capacidad máxima al recargar, y luego
 * una secuencia correcta de recarga y avance.
 *
 * @author agustinlago
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 5: Simulación de Nave Espacial ===\n");

        NaveEspacial nave = new NaveEspacial("Explorer I", 50);
        nave.mostrarEstado();

        nave.despegar();

        System.out.println("\n--- Probando avance sin combustible suficiente ---");
        nave.avanzar(30); // costo 60, no alcanza con el combustible restante
        nave.mostrarEstado();

        System.out.println("\n--- Probando recarga que supera la capacidad máxima ---");
        nave.recargarCombustible(80); // superaría los 100 de capacidad
        nave.mostrarEstado();

        System.out.println("\n--- Recargando correctamente ---");
        nave.recargarCombustible(40);
        nave.mostrarEstado();

        System.out.println("\n--- Avanzando correctamente ---");
        nave.avanzar(15);
        nave.mostrarEstado();
    }
}
