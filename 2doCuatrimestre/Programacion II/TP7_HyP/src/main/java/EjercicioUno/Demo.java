package EjercicioUno;

// Kata 1: herencia básica con super(...) y mostrarInfo() sobrescrito
public class Demo {

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 1: Vehículos ===");
        Auto auto = new Auto("Toyota", "Corolla", 4);
        auto.mostrarInfo();

        // Upcasting: la referencia es Vehiculo, pero se ejecuta la versión de Auto
        Vehiculo vehiculo = auto;
        System.out.println("-- Mediante referencia Vehiculo --");
        vehiculo.mostrarInfo();
    }
}
