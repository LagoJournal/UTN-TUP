package ejercicio6;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 6: Reserva - Cliente - Mesa ===\n");

        Cliente cliente = new Cliente("Agustín Lago", "351-5551234");
        Mesa mesa4 = new Mesa(4, 2);
        Mesa mesa7 = new Mesa(7, 4);

        Reserva reserva = new Reserva("2026-10-30", "21:00", cliente, mesa4);
        System.out.println(reserva);

        System.out.println("\n--- Cambio de mesa (agregación) ---");
        reserva.setMesa(mesa7);
        System.out.println(reserva);
        System.out.println("La mesa anterior sigue existiendo: " + mesa4);
    }
}
