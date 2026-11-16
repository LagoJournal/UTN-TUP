package EjercicioCuatro;

// Clase base concreta de los animales
public class Animal {

    protected String nombre;
    protected String especie;

    public Animal(String nombre, String especie) {
        this.nombre = nombre;
        this.especie = especie;
    }

    // Comportamiento genérico, redefinido por cada subclase
    public void hacerSonido() {
        System.out.println(nombre + " hace un sonido.");
    }

    public void describirAnimal() {
        System.out.println(nombre + " es un/a " + especie + ".");
    }
}
