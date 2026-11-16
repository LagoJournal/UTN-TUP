package Ejercicio3;

public class Curso {

    private String codigo;
    private String nombre;
    private Profesor profesor;

    public Curso(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    // Asignamos o cambiamos el profesor manteniendo ambos lados sincronizados
    public void setProfesor(Profesor p) {
        if (this.profesor == p) {
            return;
        }
        // Primero desvinculamos al profesor anterior
        Profesor anterior = this.profesor;
        this.profesor = p;
        if (anterior != null) {
            anterior.eliminarCurso(this);
        }
        // Después nos agregamos a la lista del nuevo
        if (p != null) {
            p.agregarCurso(this);
        }
    }

    public void mostrarInfo() {
        String nombreProfesor = (profesor != null) ? profesor.getNombre() : "sin asignar";
        System.out.println(codigo + " | " + nombre + " | Profesor: " + nombreProfesor);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public Profesor getProfesor() {
        return profesor;
    }
}
