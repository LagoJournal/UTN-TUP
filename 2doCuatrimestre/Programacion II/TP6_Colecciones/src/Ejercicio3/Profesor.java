package Ejercicio3;

import java.util.ArrayList;
import java.util.List;

public class Profesor {

    private String id;
    private String nombre;
    private String especialidad;
    private List<Curso> cursos;

    public Profesor(String id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.cursos = new ArrayList<>();
    }

    // Agregamos el curso y sincronizamos el otro lado si todavía no apunta a este profesor
    public void agregarCurso(Curso c) {
        if (c == null) {
            return;
        }
        if (!cursos.contains(c)) {
            cursos.add(c);
        }
        if (c.getProfesor() != this) {
            c.setProfesor(this);
        }
    }

    // Quitamos el curso y, si seguía apuntando a este profesor, lo dejamos en null
    public void eliminarCurso(Curso c) {
        if (c == null) {
            return;
        }
        cursos.remove(c);
        if (c.getProfesor() == this) {
            c.setProfesor(null);
        }
    }

    public void listarCursos() {
        if (cursos.isEmpty()) {
            System.out.println("  (sin cursos)");
        }
        for (Curso c : cursos) {
            System.out.println("  - " + c.getCodigo() + " " + c.getNombre());
        }
    }

    public void mostrarInfo() {
        System.out.println("[" + id + "] " + nombre + " | " + especialidad
                + " | Cursos: " + cursos.size());
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    // Devolvemos una copia para que nadie modifique la lista sin sincronizar
    public List<Curso> getCursos() {
        return new ArrayList<>(cursos);
    }
}
