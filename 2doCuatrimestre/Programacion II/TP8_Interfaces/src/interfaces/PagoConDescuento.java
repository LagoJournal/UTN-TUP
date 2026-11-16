package interfaces;

// Herencia entre interfaces: hereda procesarPago() y agrega el descuento
public interface PagoConDescuento extends Pago {

    double aplicarDescuento(double monto);
}
