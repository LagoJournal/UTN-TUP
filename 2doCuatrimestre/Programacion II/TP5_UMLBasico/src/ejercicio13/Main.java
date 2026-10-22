package ejercicio13;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 13: GeneradorQR - Usuario - CodigoQR ===\n");

        Usuario usuario = new Usuario("Agustín Lago", "agustin@mail.com");
        GeneradorQR generador = new GeneradorQR();

        generador.generar("https://utn.edu.ar/tup/agustin", usuario);
        generador.generar("TICKET-00042", usuario);
    }
}
