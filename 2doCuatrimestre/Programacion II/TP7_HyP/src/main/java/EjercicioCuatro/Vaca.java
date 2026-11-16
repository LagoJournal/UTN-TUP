package EjercicioCuatro;

public class Vaca extends Animal {

    public Vaca(String nombre) {
        super(nombre, "vaca");
    }

    @Override
    public void hacerSonido() {
        System.out.println(nombre + " dice: Muuu!");
    }
}
