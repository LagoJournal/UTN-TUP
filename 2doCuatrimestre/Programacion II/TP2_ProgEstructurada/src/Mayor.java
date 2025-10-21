/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author agustinlago
 */
import java.util.Scanner;

public class Mayor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el 1º número: ");
        int a = sc.nextInt();
        System.out.print("Ingrese el 2º número: ");
        int b = sc.nextInt();
        System.out.print("Ingrese el 3º número: ");
        int c = sc.nextInt();
        int mayor = a;
        if (b > mayor) mayor = b;
        if (c > mayor) mayor = c;
        System.out.println("El mayor es: " + mayor);
        sc.close();
    }
}