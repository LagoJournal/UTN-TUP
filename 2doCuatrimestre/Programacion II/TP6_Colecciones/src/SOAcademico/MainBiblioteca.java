/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SOAcademico;

/**
 *
 * @author agustinlago
 */
public class MainBiblioteca {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca("Biblioteca Córdoba");

        Autor a1 = new Autor("A1", "Julio Cortázar", "Argentina");
        Autor a2 = new Autor("A2", "Isabel Allende", "Chile");
        Autor a3 = new Autor("A3", "Gabriel García Márquez", "Colombia");

        biblioteca.agregarLibro("1", "Rayuela", 1963, a1);
        biblioteca.agregarLibro("2", "Cien años de soledad", 1967, a3);
        biblioteca.agregarLibro("3", "La casa de los espíritus", 1982, a2);
        biblioteca.agregarLibro("4", "Bestiario", 1951, a1);
        biblioteca.agregarLibro("5", "El amor en los tiempos del cólera", 1985, a3);

        System.out.println("\n Listando Libros");
        biblioteca.listarLibros();

        System.out.println("\n Buscando libro ISBN: 3");
        System.out.println(biblioteca.buscarLibroPorIsbn("3"));

        System.out.println("\n Filtrar libros de 1967");
        biblioteca.filtrarLibrosPorAnio(1967);
        
        System.out.println("\n Eliminando libro ISBN: 4");
        biblioteca.eliminarLibro("L04");
        biblioteca.listarLibros();

        System.out.println("\n Cantidad total de libos: "+ biblioteca.obtenerCantidadLibros());

        System.out.println("\n Autores disponibles:");
        biblioteca.mostrarAutoresDisponibles();
    }
}
