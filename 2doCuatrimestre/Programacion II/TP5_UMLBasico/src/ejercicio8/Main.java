package ejercicio8;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 8: Documento - FirmaDigital - Usuario ===\n");

        Usuario usuario = new Usuario("Agustín Lago", "agustin@mail.com");
        Documento doc1 = new Documento("Contrato", "Contrato de locación de servicios.",
                "a3f9c1e7", "2026-10-22", usuario);
        Documento doc2 = new Documento("Acta", "Acta de reunión de directorio.",
                "b81d02aa", "2026-10-23", usuario);

        System.out.println(doc1);
        System.out.println(doc2);

        System.out.println("\n--- Verificando composición y agregación ---");
        System.out.println("Cada documento tiene su propia firma: "
                + (doc1.getFirma() != doc2.getFirma()));
        System.out.println("Ambas firmas comparten el mismo usuario: "
                + (doc1.getFirma().getUsuario() == doc2.getFirma().getUsuario()));
    }
}
