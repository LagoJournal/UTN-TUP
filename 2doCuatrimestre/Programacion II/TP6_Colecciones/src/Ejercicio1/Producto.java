package Ejercicio1;

public class Producto {

    private String id;
    private String nombre;
    private double precio;
    private int cantidad;
    private CategoriaProducto categoria;

    public Producto(String id, String nombre, double precio, int cantidad, CategoriaProducto categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    // Mostramos todos los datos del producto en una línea
    public void mostrarInfo() {
        System.out.println("[" + id + "] " + nombre
                + " | $" + precio
                + " | Stock: " + cantidad
                + " | Categoría: " + categoria + " (" + categoria.getDescripcion() + ")");
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    // Validamos que el stock no quede negativo
    public void setCantidad(int cantidad) {
        if (cantidad >= 0) {
            this.cantidad = cantidad;
        } else {
            System.out.println("La cantidad no puede ser negativa.");
        }
    }

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Producto{id=" + id + ", nombre=" + nombre + ", precio=" + precio
                + ", cantidad=" + cantidad + ", categoria=" + categoria + "}";
    }
}
