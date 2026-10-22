package ejercicio12;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 12: Impuesto - Contribuyente - Calculadora ===\n");

        Contribuyente contribuyente = new Contribuyente("Agustín Lago", "20-40123456-7");
        Impuesto impuesto = new Impuesto(10000.0, contribuyente);

        System.out.println(impuesto);

        Calculadora calculadora = new Calculadora();
        calculadora.calcular(impuesto);
    }
}
