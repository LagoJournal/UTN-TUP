package ejercicio4;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 4: TarjetaDeCredito - Cliente - Banco ===\n");

        Banco banco = new Banco("Banco de Córdoba", "30-99922856-5");
        TarjetaDeCredito tarjeta = new TarjetaDeCredito("4509-1234-5678-9012", "12/2029", banco);
        Cliente cliente = new Cliente("Agustín Lago", "40123456");

        cliente.setTarjeta(tarjeta);
        System.out.println(tarjeta);

        System.out.println("\n--- Verificando bidireccionalidad ---");
        System.out.println("Cliente de la tarjeta: " + tarjeta.getCliente().getNombre());
        System.out.println("Tarjeta del cliente: " + cliente.getTarjeta().getNumero());

        System.out.println("\n--- Verificando agregación ---");
        System.out.println("El banco existe independiente de la tarjeta: " + banco);
    }
}
