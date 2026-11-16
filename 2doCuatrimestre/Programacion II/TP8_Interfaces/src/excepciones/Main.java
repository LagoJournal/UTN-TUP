package excepciones;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

// Parte 2: ejercicios sobre excepciones
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        divisionSegura(sc);
        conversionANumero(sc);
        lecturaArchivo(sc);
        validarEdad(sc);
        lecturaConTryWithResources(sc);

        sc.close();
    }

    // 1. División segura: lanzamos ArithmeticException con throw si el divisor es cero
    private static void divisionSegura(Scanner sc) {
        System.out.println("=== 1. División segura ===");
        try {
            System.out.print("Ingrese el dividendo: ");
            int dividendo = Integer.parseInt(sc.nextLine());
            System.out.print("Ingrese el divisor: ");
            int divisor = Integer.parseInt(sc.nextLine());

            if (divisor == 0) {
                throw new ArithmeticException("No se puede dividir por cero.");
            }
            System.out.println("Resultado: " + (dividendo / divisor));
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: debe ingresar números enteros.");
        } finally {
            System.out.println("Fin del programa de división.");
        }
        System.out.println();
    }

    // 2. Conversión de cadena a número
    private static void conversionANumero(Scanner sc) {
        System.out.println("=== 2. Conversión de cadena a número ===");
        System.out.print("Ingrese un número: ");
        String texto = sc.nextLine();
        try {
            int numero = Integer.parseInt(texto);
            System.out.println("Número convertido: " + numero);
        } catch (NumberFormatException e) {
            System.out.println("Error: \"" + texto + "\" no es un número entero válido.");
        }
        System.out.println();
    }

    // 3. Lectura de archivo: capturamos primero la excepción más específica
    private static void lecturaArchivo(Scanner sc) {
        System.out.println("=== 3. Lectura de archivo ===");
        System.out.print("Ingrese el nombre del archivo: ");
        String nombreArchivo = sc.nextLine();
        BufferedReader lector = null;
        try {
            lector = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: el archivo \"" + nombreArchivo + "\" no existe.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } finally {
            // Cerramos el recurso manualmente si se llegó a abrir
            try {
                if (lector != null) {
                    lector.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo.");
            }
        }
        System.out.println();
    }

    // 4. Excepción personalizada para validar la edad
    private static void validarEdad(Scanner sc) {
        System.out.println("=== 4. Excepción personalizada ===");
        try {
            System.out.print("Ingrese su edad: ");
            int edad = Integer.parseInt(sc.nextLine());
            verificarEdad(edad);
            System.out.println("Edad válida: " + edad);
        } catch (EdadInvalidaException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: la edad debe ser un número entero.");
        }
        System.out.println();
    }

    // Declaramos con throws porque EdadInvalidaException es checked
    private static void verificarEdad(int edad) throws EdadInvalidaException {
        if (edad < 0 || edad > 120) {
            throw new EdadInvalidaException("La edad " + edad + " está fuera del rango 0-120.");
        }
    }

    // 5. try-with-resources: el BufferedReader se cierra solo al terminar el bloque
    private static void lecturaConTryWithResources(Scanner sc) {
        System.out.println("=== 5. Lectura con try-with-resources ===");
        System.out.print("Ingrese el nombre del archivo: ");
        String nombreArchivo = sc.nextLine();
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer \"" + nombreArchivo + "\": " + e.getMessage());
        }
    }
}
