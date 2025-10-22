/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StockService;

/**
 *
 * @author agustinlago
 */
public class MainStock {
    public static void main(String[] args) {
        Inventario inv = new Inventario();

        Producto p1 = new Producto("1", "Alfajor", 500, 30, CategoriaProducto.ALIMENTOS);
        Producto p2 = new Producto("2", "Headset", 250000, 5, CategoriaProducto.ELECTRONICA);
        Producto p3 = new Producto("3", "Campera", 25000, 12, CategoriaProducto.ROPA);
        Producto p4 = new Producto("4", "Sarten", 18000, 8, CategoriaProducto.HOGAR);
        Producto p5 = new Producto("5", "Mouse", 6000, 20, CategoriaProducto.ELECTRONICA);

        inv.agregarProducto(p1);
        inv.agregarProducto(p2);
        inv.agregarProducto(p3);
        inv.agregarProducto(p4);
        inv.agregarProducto(p5);

        System.out.println("Lista de productos:");
        inv.listarProductos();

        System.out.println("\nBuscando producto ID: 3");
        System.out.println(inv.buscarProductoPorId("3"));

        System.out.println("\nProductos categoría ELECTRONICA:");
        inv.filtrarPorCategoria(CategoriaProducto.ELECTRONICA);

        System.out.println("\nEliminando producto ID: 1");
        inv.eliminarProducto("1");
        inv.listarProductos();

        System.out.println("\nActualizando stock producto ID: 3");
        inv.actualizarStock("3", 25);
        inv.listarProductos();

        System.out.println("\nStock total: " + inv.obtenerTotalStock());

        System.out.println("\nProducto con mayor stock:");
        System.out.println(inv.obtenerProductoConMayorStock());

        System.out.println("\nProductos con precio entre 1000 y 30000:");
        inv.filtrarProductosPorPrecio(1000, 30000);

        System.out.println("\nCategorías disponibles:");
        inv.mostrarCategoriasDisponibles();
    }   
}
