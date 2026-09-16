package Ejercicio_04;

/**
 * Prueba la clase Producto: ambos constructores, las dos variantes de
 * aplicarDescuento, el rechazo de precios negativos y el impacto de
 * cambiar el IVA global sobre los precios finales.
 *
 * @author agustinlago
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Caso 4: Inventario de Productos con IVA ===\n");

        Producto p1 = new Producto("Teclado mecánico", 25000);
        Producto p2 = new Producto("Mouse");

        System.out.println("--- Estado inicial ---");
        System.out.println(p1);
        System.out.println(p2);

        System.out.println("\n--- Probando aplicarDescuento ---");
        System.out.println("Descuento 10% sobre " + p1.getNombre() + ": $" + p1.aplicarDescuento(10));
        System.out.println("Descuento 80% con mínimo $10000 sobre " + p1.getNombre() + ": $" + p1.aplicarDescuento(80, 10000));

        System.out.println("\n--- Probando robustez con precio negativo ---");
        Producto p3 = new Producto("Producto inválido", -500);
        System.out.println(p3);

        System.out.println("\n--- Cambiando el IVA global a 27% ---");
        Producto.cambiarIVA(0.27);
        System.out.println(p1);
        System.out.println(p2);
    }
}
