package Ejercicio_05;

/**
 * Representa una cuenta bancaria simple. El número de cuenta es inmutable
 * y se autogenera a partir de un contador estático; totalCuentas lleva la
 * cuenta global de cuentas creadas. Ninguno de los dos posee setter.
 *
 * @author agustinlago
 */
public class Cuenta {
    private final int numero;
    private String titular;
    private double saldo;

    private static int ultimoNumero = 100;
    private static int totalCuentas = 0;

    // Constructor principal.
    public Cuenta(String titular, double saldoInicial) {
        this.numero = ++ultimoNumero;
        setTitular(titular);
        if (saldoInicial < 0) {
            System.out.println("Error: el saldo inicial no puede ser negativo. Se inicia en $0.");
            this.saldo = 0;
        } else {
            this.saldo = saldoInicial;
        }
        totalCuentas++;
    }

    // Constructor parcial (DRY): saldo inicial en 0, delega en el principal.
    public Cuenta(String titular) {
        this(titular, 0);
    }

    // ---- Getters (sin setter para numero ni totalCuentas) ----
    public int getNumero() { return numero; }
    public String getTitular() { return titular; }
    public static int mostrarTotalCuentas() { return totalCuentas; }

    // ---- Setters con validación ----
    public void setTitular(String titular) {
        if (titular == null || titular.trim().isEmpty()) {
            System.out.println("Error: el titular no puede estar vacío.");
            return;
        }
        this.titular = titular;
    }

    // ---- Métodos sobrecargados ----
    public double consultarSaldo() {
        return saldo;
    }

    public double consultarSaldo(double cotizacionDolar) {
        if (cotizacionDolar <= 0) {
            System.out.println("Error: la cotización del dólar debe ser positiva.");
            return 0;
        }
        return saldo / cotizacionDolar;
    }

    // ---- Transacciones seguras (única vía para modificar el saldo) ----
    public void depositar(double monto) {
        if (monto <= 0) {
            System.out.println("Error: el monto a depositar debe ser positivo.");
            return;
        }
        saldo += monto;
        System.out.println("Depósito de $" + monto + " realizado. Saldo actual: $" + String.format("%.2f", saldo));
    }

    public void extraer(double monto) {
        if (monto <= 0) {
            System.out.println("Error: el monto a extraer debe ser positivo.");
            return;
        }
        if (monto > saldo) {
            System.out.println("Error: fondos insuficientes. Saldo disponible: $" + String.format("%.2f", saldo));
            return;
        }
        saldo -= monto;
        System.out.println("Extracción de $" + monto + " realizada. Saldo actual: $" + String.format("%.2f", saldo));
    }

    @Override
    public String toString() {
        return "Cuenta #" + numero + " - Titular: " + titular + " - Saldo: $" + String.format("%.2f", saldo);
    }
}
