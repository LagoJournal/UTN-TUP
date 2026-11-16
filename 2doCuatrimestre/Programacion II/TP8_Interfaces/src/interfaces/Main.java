package interfaces;

// Parte 1: sistema de e-commerce con interfaces
public class Main {

    public static void main(String[] args) {
        // Creamos clientes, productos y pedidos
        Cliente ana = new Cliente("Ana");
        Cliente luis = new Cliente("Luis");

        Pedido pedido1 = new Pedido(ana);
        pedido1.agregarProducto(new Producto("Teclado", 25000));
        pedido1.agregarProducto(new Producto("Mouse", 12000));

        Pedido pedido2 = new Pedido(luis);
        pedido2.agregarProducto(new Producto("Monitor", 180000));

        // Pagamos cada pedido con un medio distinto
        System.out.println("=== Pedido 1 ===");
        pedido1.mostrarDetalle();
        pedido1.pagar(new PagoConTarjetaCredito("4509123412341234"));

        System.out.println();
        System.out.println("=== Pedido 2 ===");
        pedido2.mostrarDetalle();
        pedido2.pagar(new PagoConPayPal("luis@mail.com"));
    }
}
