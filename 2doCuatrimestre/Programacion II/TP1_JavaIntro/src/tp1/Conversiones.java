/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp1;

import java.util.Scanner;

/**
 *
 * @author agustinlago
 */
public class Conversiones {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa un número entero: ");
        int num1 = scanner.nextInt();
        System.out.print("Ingresa otro número entero: ");
        int num2 = scanner.nextInt();

        System.out.println("División entera: " + (num1 / num2));

        double d1 = num1;
        double d2 = num2;
        System.out.println("División con decimales: " + (d1 / d2));
    }
}