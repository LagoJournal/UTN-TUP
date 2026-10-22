package ejercicio1;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 1: Pasaporte - Foto - Titular ===\n");

        Pasaporte pasaporte = new Pasaporte("AAA123456", "2024-03-15", "foto_agustin.jpg", "JPG");
        Titular titular = new Titular("Agustín Lago", "40123456");

        pasaporte.setTitular(titular);

        System.out.println(pasaporte);
        System.out.println("\n--- Verificando bidireccionalidad ---");
        System.out.println("Titular del pasaporte: " + pasaporte.getTitular().getNombre());
        System.out.println("Pasaporte del titular: " + titular.getPasaporte().getNumero());

        System.out.println("\n--- Verificando composición ---");
        System.out.println("Foto creada por el pasaporte: " + pasaporte.getFoto());
    }
}
