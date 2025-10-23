/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp8_interfaces.ejercicio1;

/**
 *
 * @author agustinlago
 */
public class TarjetaCredito implements Pago, PagoConDescuento {
    @Override
    public void procesarPago(double monto) {
        System.out.println("Pago con tarjeta de: $" + monto);
    }

    @Override
    public double aplicarDescuento(double monto) {
        return monto * 0.9;
    }
}
