/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academico;

/**
 *
 * @author agustinlago
 */
public class Curso {
    private String codigo;
    private String nombre;
    private Profesor profesor;

    public Curso(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public Profesor getProfesor() { return profesor; }

    public void setProfesor(Profesor p) {
        if (this.profesor != null && this.profesor != p) {
            this.profesor.getCursos().remove(this);
        }

        this.profesor = p;

        if (p != null && !p.getCursos().contains(this)) {
            p.getCursos().add(this);
        }
    }

    public void mostrarInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "{ \"codigo\": \"" + codigo + "\", " +
               "\"nombre\": \"" + nombre + "\", " +
               "\"profesor\": " + (profesor != null ? "\"" + profesor.getNombre() + "\"" : null) + " }";
    }
}
