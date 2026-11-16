package interfaces;

public class PagoConPayPal implements PagoConDescuento {

    private String email;

    public PagoConPayPal(String email) {
        this.email = email;
    }

    // Aplicamos un 5% de descuento por pago con PayPal
    @Override
    public double aplicarDescuento(double monto) {
        return monto * 0.95;
    }

    @Override
    public void procesarPago(double monto) {
        System.out.printf("Pago de $%.2f procesado con PayPal (%s)%n", monto, email);
    }
}
