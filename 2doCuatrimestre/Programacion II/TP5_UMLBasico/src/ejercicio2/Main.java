package ejercicio2;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 2: Celular - Bateria - Usuario ===\n");

        Bateria bateria = new Bateria("BL-5000", 5000);
        Celular celular = new Celular("356938035643809", "Motorola", "G84", bateria);
        Usuario usuario = new Usuario("Agustín Lago", "40123456");

        celular.setUsuario(usuario);
        System.out.println(celular);

        System.out.println("\n--- Verificando bidireccionalidad ---");
        System.out.println("Celular del usuario: " + usuario.getCelular().getModelo());

        System.out.println("\n--- Verificando agregación ---");
        Bateria nueva = new Bateria("BL-6000", 6000);
        celular.setBateria(nueva);
        System.out.println("Batería nueva en el celular: " + celular.getBateria());
        System.out.println("La batería anterior sigue existiendo: " + bateria);
    }
}
