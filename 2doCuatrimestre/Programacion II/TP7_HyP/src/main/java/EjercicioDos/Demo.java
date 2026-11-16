package EjercicioDos;

// Kata 2: array de figuras y cálculo polimórfico del área
public class Demo {

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 2: Figuras ===");
        Figura[] figuras = new Figura[3];
        figuras[0] = new Circulo(2.5);
        figuras[1] = new Rectangulo(4, 3);
        figuras[2] = new Circulo(1);

        // Recorremos el array; cada objeto resuelve su propio calcularArea()
        for (int i = 0; i < figuras.length; i++) {
            System.out.printf("%s -> área: %.2f%n", figuras[i].getNombre(), figuras[i].calcularArea());
        }
    }
}
