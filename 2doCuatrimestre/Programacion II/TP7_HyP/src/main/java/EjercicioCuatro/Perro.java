package EjercicioCuatro;

public class Perro extends Animal {

    public Perro(String nombre) {
        super(nombre, "perro");
    }

    @Override
    public void hacerSonido() {
        System.out.println(nombre + " dice: Guau!");
    }
}
