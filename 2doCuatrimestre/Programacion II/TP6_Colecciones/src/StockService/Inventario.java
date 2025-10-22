/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StockService;

/**
 *
 * @author agustinlago
 */
import java.util.ArrayList;

public class Inventario {
    private ArrayList<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public void listarProductos() {
        productos.forEach(Producto::mostrarInfo);
    }

    public Producto buscarProductoPorId(String id) {
        for (Producto p : productos)
            if (p.getId().equalsIgnoreCase(id))
                return p;
        return null;
    }

    public void eliminarProducto(String id) {
        productos.removeIf(p -> p.getId().equalsIgnoreCase(id));
    }

    public void actualizarStock(String id, int nuevaCantidad) {
        Producto p = buscarProductoPorId(id);
        if (p != null) p.setCantidad(nuevaCantidad);
    }

    public void filtrarPorCategoria(CategoriaProducto categoria) {
        for (Producto p : productos)
            if (p.getCategoria() == categoria)
                p.mostrarInfo();
    }

    public int obtenerTotalStock() {
        int total = 0;
        for (Producto p : productos)
            total += p.getCantidad();
        return total;
    }

    public Producto obtenerProductoConMayorStock() {
        if (productos.isEmpty()) return null;
        Producto max = productos.get(0);
        for (Producto p : productos)
            if (p.getCantidad() > max.getCantidad())
                max = p;
        return max;
    }

    public void filtrarProductosPorPrecio(double min, double max) {
        for (Producto p : productos)
            if (p.getPrecio() >= min && p.getPrecio() <= max)
                p.mostrarInfo();
    }

    public void mostrarCategoriasDisponibles() {
        for (CategoriaProducto c : CategoriaProducto.values())
            System.out.println(c + " (" + c.getDescripcion() + ")");
    }
}
