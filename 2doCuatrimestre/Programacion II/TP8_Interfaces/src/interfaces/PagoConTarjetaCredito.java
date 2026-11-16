package interfaces;

// Implementa PagoConDescuento, por lo que debe cumplir ambos contratos
public class PagoConTarjetaCredito implements PagoConDescuento {

    private String numeroTarjeta;

    public PagoConTarjetaCredito(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    // Aplicamos un 10% de descuento por pago con tarjeta
    @Override
    public double aplicarDescuento(double monto) {
        return monto * 0.90;
    }

    @Override
    public void procesarPago(double monto) {
        System.out.printf("Pago de $%.2f procesado con tarjeta terminada en %s%n",
                monto, numeroTarjeta.substring(numeroTarjeta.length() - 4));
    }
}
