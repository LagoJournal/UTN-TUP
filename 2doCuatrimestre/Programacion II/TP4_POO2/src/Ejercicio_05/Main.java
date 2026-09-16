package Ejercicio_05;

/**
 * Prueba la clase Cuenta: ambos constructores, autoincremento del número
 * de cuenta, validación de extracciones, conversión de saldo a dólares y
 * el contador estático de cuentas totales.
 *
 * @author agustinlago
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Caso 5: Banca Simple - Cuentas ===\n");

        Cuenta cuenta1 = new Cuenta("Agustín Lago", 2000);
        Cuenta cuenta2 = new Cuenta("María Torres");
        Cuenta cuenta3 = new Cuenta("Luis Pérez", 500);

        System.out.println("--- Autoincremento de números de cuenta ---");
        System.out.println(cuenta1);
        System.out.println(cuenta2);
        System.out.println(cuenta3);

        System.out.println("\n--- Probando extracción mayor al saldo disponible ---");
        cuenta2.extraer(100); // saldo 0, debe rechazarse

        System.out.println("\n--- Depósitos y extracciones válidas ---");
        cuenta2.depositar(1500);
        cuenta2.extraer(300);

        System.out.println("\n--- Probando conversión de saldo a dólares ---");
        System.out.println(cuenta1.getTitular() + " tiene USD " + String.format("%.2f", cuenta1.consultarSaldo(1360)));

        System.out.println("\n--- Total de cuentas creadas ---");
        System.out.println("Total: " + Cuenta.mostrarTotalCuentas());
    }
}
