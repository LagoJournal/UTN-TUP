/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author agustinlago
 */

import java.util.Scanner;

public class Bisiesto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese un año: ");
        int año = sc.nextInt();
        boolean esBisiesto = (año % 4 == 0 && año % 100 != 0) || (año % 400 == 0);
        if (esBisiesto) {
            System.out.println(año + " es año bisiesto.");
        } else {
            System.out.println(año + " no es año bisiesto.");
        }
        sc.close();
    }
}
