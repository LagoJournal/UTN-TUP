/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author agustinlago
 */

import java.util.Scanner;

public class CalculadoraDescuento {
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
                System.out.println("Categoría inválida, no se aplicara descuento");
        }
        double descuento = precio * (porcentaje / 100.0);
        double precioFinal = precio - descuento;
        System.out.println("Precio original: " + precio);
        System.out.println("Descuento aplicado: " + porcentaje + "%");
        System.out.println("Precio final: " + precioFinal);
        sc.close();
    }
}
