/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author agustinlago
 */
import java.util.Scanner;

public class PrecioEcommerce {
    public static double calcularPrecioFinal(double precioBase, double impuestoPct, double descuentoPct) {
        double impuesto = precioBase * (impuestoPct / 100.0);
        double descuento = precioBase * (descuentoPct / 100.0);
        return precioBase + impuesto - descuento;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el precio base del producto: ");
        double base = sc.nextDouble();
        System.out.print("Ingrese el impuesto en porcentaje: ");
        double imp = sc.nextDouble();
        System.out.print("Ingrese el descuento en porcentaje: ");
        double des = sc.nextDouble();
        double finalPrice = calcularPrecioFinal(base, imp, des);
        System.out.println("El precio final del producto es: " + finalPrice);
        sc.close();
    }
}
