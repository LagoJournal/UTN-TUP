import java.util.Scanner;

public class Ejercicio_04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el precio del producto: ");
        double precio = sc.nextDouble();
        System.out.print("Ingrese la categoría del producto (A, B o C): ");
        String cat = sc.next().trim().toUpperCase();
        double porcentaje = 0.0;
        switch (cat) {
            case "A": porcentaje = 10; break;
            case "B": porcentaje = 15; break;
            case "C": porcentaje = 20; break;
            default:
                System.out.println("Categoría inválida.");
        }
        double precioFinal = precio - precio * (porcentaje / 100.0);
        System.out.println("Descuento aplicado: " + (int) porcentaje + "%");
        System.out.println("Precio final: " + precioFinal);
    }
}
