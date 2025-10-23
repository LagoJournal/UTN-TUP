/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp8_interfaces;

/**
 *
 * @author agustinlago
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileRead {
    public static void main(String[] args) {
        try {
            Scanner archivo = new Scanner(new File("archivo.txt"));
            while (archivo.hasNextLine()) {
                System.out.println(archivo.nextLine());
            }
            archivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Archivo no encontrado.");
        }
    }
}
