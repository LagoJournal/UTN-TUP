package EjercicioCuatro;

public class Gato extends Animal {

    public Gato(String nombre) {
        super(nombre, "gato");
    }

    @Override
    public void hacerSonido() {
        System.out.println(nombre + " dice: Miau!");
    }
}
