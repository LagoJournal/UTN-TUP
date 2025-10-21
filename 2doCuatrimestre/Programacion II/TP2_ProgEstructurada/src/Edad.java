/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author agustinlago
 */

import java.util.Scanner;

public class Edad {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese su edad: ");
        int edad = sc.nextInt();
        if (edad < 12) {
            System.out.println("Niño.");
        } else if (edad <= 17) {
            System.out.println("Adolescente.");
        } else if (edad <= 59) {
            System.out.println("Adulto.");
        } else {
            System.out.println("Adulto mayor.");
        }
        sc.close();
    }
}
