package ejercicio7;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 7: Vehiculo - Motor - Conductor ===\n");

        Motor motor = new Motor("Nafta 1.6", "MTR-55021");
        Vehiculo vehiculo = new Vehiculo("AF123BC", "Fiat Cronos", motor);
        Conductor conductor = new Conductor("Agustín Lago", "B1-40123456");

        conductor.setVehiculo(vehiculo);
        System.out.println(vehiculo);

        System.out.println("\n--- Verificando bidireccionalidad ---");
        System.out.println("Conductor del vehículo: " + vehiculo.getConductor().getNombre());
        System.out.println("Vehículo del conductor: " + conductor.getVehiculo().getPatente());

        System.out.println("\n--- Verificando agregación ---");
        System.out.println("El motor existe por sí mismo: " + motor);
    }
}
