package EjercicioCuatro;

import java.util.ArrayList;

// Kata 4: lista de animales con hacerSonido() sobrescrito
public class Demo {

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 4: Animales ===");
        ArrayList<Animal> animales = new ArrayList<>();
        animales.add(new Perro("Firulais"));
        animales.add(new Gato("Michi"));
        animales.add(new Vaca("Lola"));

        // La llamada es la misma; el comportamiento depende del objeto real
        for (Animal a : animales) {
            a.describirAnimal();
            a.hacerSonido();
        }
    }
}
