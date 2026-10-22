package ejercicio10;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 10: CuentaBancaria - ClaveSeguridad - Titular ===\n");

        CuentaBancaria cuenta = new CuentaBancaria("0200300011000012345678", 150000.0, "8421", "2026-01-10");
        Titular titular = new Titular("Agustín Lago", "40123456");

        titular.setCuenta(cuenta);
        System.out.println(cuenta);

        System.out.println("\n--- Verificando bidireccionalidad ---");
        System.out.println("Titular de la cuenta: " + cuenta.getTitular().getNombre());
        System.out.println("CBU del titular: " + titular.getCuenta().getCbu());

        System.out.println("\n--- Cambio de clave (composición) ---");
        cuenta.getClave().cambiarCodigo("12", "2026-10-22");
        cuenta.getClave().cambiarCodigo("9137", "2026-10-22");
        System.out.println("Clave: " + cuenta.getClave());
    }
}
