package interfaces;

public class Producto implements Pagable {

    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    // El total de un producto es su precio
    @Override
    public double calcularTotal() {
        return precio;
    }
}
