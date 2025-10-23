/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp8_interfaces;

/**
 *
 * @author agustinlago
 */
import java.util.Scanner;

public class ValidarEdad {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese su edad: ");
        int edad = sc.nextInt();

        try {
            if (edad < 0 || edad > 120) {
                throw new EdadInvalidaException("Edad inválida: " + edad);
            } else {
                System.out.println("Edad válida: " + edad);
            }
        } catch (EdadInvalidaException e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }
    }
}