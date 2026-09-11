import java.util.Scanner;

public class Ejercicio_05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sumaPares = 0;
        int n;
        do {
            System.out.print("Ingrese un número (0 para terminar): ");
            n = sc.nextInt();
            if (n != 0 && n % 2 == 0) sumaPares += n;
        } while (n != 0);
        System.out.println("La suma de los números pares es: " + sumaPares);
    }
}
