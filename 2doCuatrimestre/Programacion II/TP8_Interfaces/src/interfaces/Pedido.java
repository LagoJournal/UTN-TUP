package interfaces;

import java.util.ArrayList;

public class Pedido implements Pagable {

    private ArrayList<Producto> productos;
    private Cliente cliente;
    private EstadoPedido estado;

    // Todo pedido nuevo arranca en estado PENDIENTE
    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.estado = EstadoPedido.PENDIENTE;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    // Sumamos el total de cada producto; Producto también es Pagable
    @Override
    public double calcularTotal() {
        double total = 0;
        for (Producto p : productos) {
            total += p.calcularTotal();
        }
        return total;
    }

    // Cambiamos el estado y avisamos al cliente
    public void cambiarEstado(EstadoPedido nuevoEstado) {
        this.estado = nuevoEstado;
        cliente.notificarCambioDeEstado(nuevoEstado);
    }

    // Recibimos cualquier medio que cumpla PagoConDescuento
    public void pagar(PagoConDescuento medioPago) {
        cambiarEstado(EstadoPedido.EN_PROCESO);
        double totalFinal = medioPago.aplicarDescuento(calcularTotal());
        medioPago.procesarPago(totalFinal);
        cambiarEstado(EstadoPedido.PAGADO);
    }

    public void mostrarDetalle() {
        System.out.println("Pedido de " + cliente.getNombre() + " (" + estado + ")");
        for (Producto p : productos) {
            System.out.printf("  - %s: $%.2f%n", p.getNombre(), p.calcularTotal());
        }
        System.out.printf("  Total: $%.2f%n", calcularTotal());
    }
}
