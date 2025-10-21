/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp3_poo;

/**
 *
 * @author agustinlago
 */
import java.time.Year;

public class Libro {
    private final String titulo;
    private final String autor;
    private int anioPublicacion;

    public Libro(String titulo, String autor, int anioPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        setAnioPublicacion(anioPublicacion);
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnioPublicacion() { return anioPublicacion; }

    public boolean setAnioPublicacion(int anio) {
        int actual = Year.now().getValue();
        if (anio > 0 && anio <= actual) {
            this.anioPublicacion = anio;
            return true;
        } else {
            System.out.println("Valor inválido para año de publicacion: " + anio);
            return false;
        }
    }

    public void mostrarInfo() {
        System.out.println("Libro:" + titulo + " - " + autor + " (" + anioPublicacion + ")");
    }
}
