/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp8_interfaces.ejercicio1;

/**
 *
 * @author agustinlago
 */
import java.util.List;

public class Pedido implements Pagable {
    private List<Producto> productos;

    public Pedido(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public double calcularTotal() {
        return productos.stream().mapToDouble(Producto::calcularTotal).sum();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
}

