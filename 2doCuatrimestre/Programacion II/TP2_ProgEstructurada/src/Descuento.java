/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author agustinlago
 */
import java.util.Scanner;

public class Descuento {
    
    private static final double PORCENTAJE_DESCUENTO = 0.10;

    public static double calcularDescuentoEspecial(double precio) {
        double descuentoAplicado = precio * PORCENTAJE_DESCUENTO;
        System.out.println("El descuento especial aplicado es: " + descuentoAplicado);
        return precio - descuentoAplicado;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el precio del producto: ");
        double precio = sc.nextDouble();
        double finalPrice = calcularDescuentoEspecial(precio);
        System.out.println("El precio final con descuento es: " + finalPrice);
        sc.close();
    }
}
