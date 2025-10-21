/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author agustinlago
 */
import java.util.Scanner;

public class SumaPares {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sumaPares = 0;
        while (true) {
            System.out.print("Ingrese un número: ");
            int n = sc.nextInt();
            if (n == 0) break;
            if (n % 2 == 0) sumaPares += n;
        }
        System.out.println("La suma de los números pares es: " + sumaPares);
        sc.close();
    }
}
