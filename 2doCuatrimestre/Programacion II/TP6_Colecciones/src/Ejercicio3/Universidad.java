package Ejercicio3;

import java.util.ArrayList;
import java.util.List;

public class Universidad {

    private String nombre;
    private List<Profesor> profesores;
    private List<Curso> cursos;

    public Universidad(String nombre) {
        this.nombre = nombre;
        this.profesores = new ArrayList<>();
        this.cursos = new ArrayList<>();
    }

    public void agregarProfesor(Profesor p) {
        if (buscarProfesorPorId(p.getId()) == null) {
            profesores.add(p);
        }
    }

    public void agregarCurso(Curso c) {
        if (buscarCursoPorCodigo(c.getCodigo()) == null) {
            cursos.add(c);
        }
    }

    // Delegamos en setProfesor, que es quien mantiene el invariante
    public boolean asignarProfesorACurso(String codigoCurso, String idProfesor) {
        Curso c = buscarCursoPorCodigo(codigoCurso);
        Profesor p = buscarProfesorPorId(idProfesor);
        if (c == null || p == null) {
            System.out.println("No se pudo asignar: curso o profesor inexistente.");
            return false;
        }
        c.setProfesor(p);
        return true;
    }

    public void listarProfesores() {
        for (Profesor p : profesores) {
            p.mostrarInfo();
            p.listarCursos();
        }
    }

    public void listarCursos() {
        for (Curso c : cursos) {
            c.mostrarInfo();
        }
    }

    public Profesor buscarProfesorPorId(String id) {
        for (Profesor p : profesores) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public Curso buscarCursoPorCodigo(String codigo) {
        for (Curso c : cursos) {
            if (c.getCodigo().equals(codigo)) {
                return c;
            }
        }
        return null;
    }

    // Rompemos la relación con el profesor antes de quitar el curso
    public boolean eliminarCurso(String codigo) {
        Curso c = buscarCursoPorCodigo(codigo);
        if (c == null) {
            return false;
        }
        c.setProfesor(null);
        cursos.remove(c);
        return true;
    }

    // Recorremos una copia de la lista porque setProfesor(null) la modifica
    public boolean eliminarProfesor(String id) {
        Profesor p = buscarProfesorPorId(id);
        if (p == null) {
            return false;
        }
        for (Curso c : p.getCursos()) {
            c.setProfesor(null);
        }
        profesores.remove(p);
        return true;
    }

    public void mostrarReporteCursosPorProfesor() {
        for (Profesor p : profesores) {
            System.out.println(p.getNombre() + ": " + p.getCursos().size() + " curso(s)");
        }
    }

    public String getNombre() {
        return nombre;
    }
}
