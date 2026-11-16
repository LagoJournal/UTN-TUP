package Ejercicio1;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Inventario inventario = new Inventario();

        // 1. Creamos cinco productos de distintas categorías
        System.out.println("=== 1. Carga de productos ===");
        inventario.agregarProducto(new Producto("P01", "Arroz 1kg", 1200, 50, CategoriaProducto.ALIMENTOS));
        inventario.agregarProducto(new Producto("P02", "Auriculares", 25000, 10, CategoriaProducto.ELECTRONICA));
        inventario.agregarProducto(new Producto("P03", "Remera algodón", 2800, 30, CategoriaProducto.ROPA));
        inventario.agregarProducto(new Producto("P04", "Juego de sábanas", 15000, 8, CategoriaProducto.HOGAR));
        inventario.agregarProducto(new Producto("P05", "Yerba 1kg", 2500, 80, CategoriaProducto.ALIMENTOS));
        inventario.agregarProducto(new Producto("P01", "Duplicado", 100, 1, CategoriaProducto.HOGAR));

        // 2. Listado completo
        System.out.println("\n=== 2. Listado de productos ===");
        inventario.listarProductos();

        // 3. Búsqueda por id (válida e inválida)
        System.out.println("\n=== 3. Búsqueda por ID ===");
        mostrarBusqueda(inventario, "P03");
        mostrarBusqueda(inventario, "P99");

        // 4. Filtro por categoría
        System.out.println("\n=== 4. Productos de ALIMENTOS ===");
        mostrarLista(inventario.filtrarPorCategoria(CategoriaProducto.ALIMENTOS));

        // 5. Eliminación y listado restante
        System.out.println("\n=== 5. Eliminar P02 ===");
        System.out.println(inventario.eliminarProducto("P02") ? "Producto eliminado." : "No se encontró el producto.");
        inventario.listarProductos();

        // 6. Actualización de stock
        System.out.println("\n=== 6. Actualizar stock de P04 a 25 ===");
        if (inventario.actualizarStock("P04", 25)) {
            inventario.buscarProductoPorId("P04").mostrarInfo();
        }

        // 7. Total de stock
        System.out.println("\n=== 7. Total de stock ===");
        System.out.println("Unidades totales: " + inventario.obtenerTotalStock());

        // 8. Producto con mayor stock
        System.out.println("\n=== 8. Producto con mayor stock ===");
        inventario.obtenerProductoConMayorStock().mostrarInfo();

        // 9. Filtro por rango de precios
        System.out.println("\n=== 9. Productos entre $1000 y $3000 ===");
        mostrarLista(inventario.filtrarProductosPorPrecio(1000, 3000));

        // 10. Categorías del enum
        System.out.println("\n=== 10. Categorías disponibles ===");
        inventario.mostrarCategoriasDisponibles();
    }

    private static void mostrarBusqueda(Inventario inventario, String id) {
        Producto p = inventario.buscarProductoPorId(id);
        if (p != null) {
            p.mostrarInfo();
        } else {
            System.out.println("No existe un producto con id " + id + ".");
        }
    }

    private static void mostrarLista(ArrayList<Producto> lista) {
        if (lista.isEmpty()) {
            System.out.println("Sin resultados.");
        }
        for (Producto p : lista) {
            p.mostrarInfo();
        }
    }
}
