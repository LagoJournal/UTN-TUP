/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academico;

/**
 *
 * @author agustinlago
 */
public class MainAcademico {
    public static void main(String[] args) {
        Universidad uni = new Universidad("Universidad Nacional");

        Profesor p1 = new Profesor("1", "Ana Torres", "Matemática");
        Profesor p2 = new Profesor("2", "Carlos Díaz", "Programación");
        Profesor p3 = new Profesor("3", "Lucía Gómez", "Historia");

        uni.agregarProfesor(p1);
        uni.agregarProfesor(p2);
        uni.agregarProfesor(p3);

        Curso c1 = new Curso("1", "Álgebra");
        Curso c2 = new Curso("2", "POO en Java");
        Curso c3 = new Curso("3", "Historia Moderna");
        Curso c4 = new Curso("4", "Estructuras de Datos");
        Curso c5 = new Curso("5", "Análisis Numérico");

        uni.agregarCurso(c1);
        uni.agregarCurso(c2);
        uni.agregarCurso(c3);
        uni.agregarCurso(c4);
        uni.agregarCurso(c5);

        System.out.println("\nAsignar profesores a cursos:");
        uni.asignarProfesorACurso("1", "1");
        uni.asignarProfesorACurso("5", "1");
        uni.asignarProfesorACurso("2", "2");
        uni.asignarProfesorACurso("4", "2");
        uni.asignarProfesorACurso("3", "3");

        System.out.println("\nListado de profesores:");
        uni.listarProfesores();

        System.out.println("\nListado de cursos:");
        uni.listarCursos();

        System.out.println("\nCambiar profesor de 1 a 3:");
        uni.asignarProfesorACurso("1", "3");

        System.out.println("\nCursos actualizados:");
        uni.listarCursos();

        System.out.println("\nEliminar curso 2:");
        uni.eliminarCurso("2");
        uni.listarCursos();

        System.out.println("\nEliminar profesor 1:");
        uni.eliminarProfesor("1");
        uni.listarProfesores();

        System.out.println("\nCantidad de cursos por profesor:");
        for (Profesor p : new Profesor[]{p1, p2, p3}) {
            System.out.println(p.getNombre() + " dicta " + p.getCursos().size() + " cursos.");
        }
    }
}