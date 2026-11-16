package Ejercicio3;

public class Main {

    public static void main(String[] args) {
        Universidad utn = new Universidad("UTN");

        // 1 y 2. Creamos tres profesores y cinco cursos y los damos de alta
        utn.agregarProfesor(new Profesor("PR1", "Ana Gómez", "Programación"));
        utn.agregarProfesor(new Profesor("PR2", "Luis Pérez", "Bases de Datos"));
        utn.agregarProfesor(new Profesor("PR3", "María Díaz", "Matemática"));

        utn.agregarCurso(new Curso("C01", "Programación I"));
        utn.agregarCurso(new Curso("C02", "Programación II"));
        utn.agregarCurso(new Curso("C03", "Bases de Datos I"));
        utn.agregarCurso(new Curso("C04", "Matemática"));
        utn.agregarCurso(new Curso("C05", "Estadística"));

        // 3. Asignamos profesores a los cursos
        utn.asignarProfesorACurso("C01", "PR1");
        utn.asignarProfesorACurso("C02", "PR1");
        utn.asignarProfesorACurso("C03", "PR2");
        utn.asignarProfesorACurso("C04", "PR3");
        utn.asignarProfesorACurso("C05", "PR3");
        utn.asignarProfesorACurso("C99", "PR1");

        // 4. Listamos desde ambos lados de la relación
        System.out.println("\n=== 4. Cursos con su profesor ===");
        utn.listarCursos();
        System.out.println("\n=== 4. Profesores con sus cursos ===");
        utn.listarProfesores();

        // 5. Cambiamos el profesor de C02 y verificamos los dos lados
        System.out.println("\n=== 5. C02 pasa de PR1 a PR2 ===");
        utn.asignarProfesorACurso("C02", "PR2");
        Curso c02 = utn.buscarCursoPorCodigo("C02");
        Profesor pr1 = utn.buscarProfesorPorId("PR1");
        Profesor pr2 = utn.buscarProfesorPorId("PR2");
        c02.mostrarInfo();
        System.out.println("PR1 contiene C02: " + pr1.getCursos().contains(c02));
        System.out.println("PR2 contiene C02: " + pr2.getCursos().contains(c02));

        // 6. Eliminamos un curso y verificamos que no queda en la lista del profesor
        System.out.println("\n=== 6. Eliminar curso C05 ===");
        Curso c05 = utn.buscarCursoPorCodigo("C05");
        Profesor pr3 = utn.buscarProfesorPorId("PR3");
        utn.eliminarCurso("C05");
        System.out.println("C05 sigue en la universidad: " + (utn.buscarCursoPorCodigo("C05") != null));
        System.out.println("PR3 contiene C05: " + pr3.getCursos().contains(c05));
        System.out.println("Profesor de C05: " + c05.getProfesor());

        // 7. Eliminamos un profesor: sus cursos quedan sin profesor
        System.out.println("\n=== 7. Eliminar profesor PR1 ===");
        utn.eliminarProfesor("PR1");
        System.out.println("PR1 sigue en la universidad: " + (utn.buscarProfesorPorId("PR1") != null));
        utn.listarCursos();

        // 8. Reporte final
        System.out.println("\n=== 8. Cursos por profesor ===");
        utn.mostrarReporteCursosPorProfesor();
    }
}
