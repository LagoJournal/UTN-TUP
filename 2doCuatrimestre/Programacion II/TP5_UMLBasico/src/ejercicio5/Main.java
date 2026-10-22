package ejercicio5;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 5: Computadora - PlacaMadre - Propietario ===\n");

        Computadora pc = new Computadora("Lenovo", "SN-88213", "B550M Pro", "AMD B550");
        Propietario propietario = new Propietario("Agustín Lago", "40123456");

        pc.setPropietario(propietario);
        System.out.println(pc);

        System.out.println("\n--- Verificando bidireccionalidad ---");
        System.out.println("Computadora del propietario: " + propietario.getComputadora().getNumeroSerie());

        System.out.println("\n--- Verificando composición ---");
        System.out.println("Placa madre creada por la computadora: " + pc.getPlacaMadre());
    }
}
