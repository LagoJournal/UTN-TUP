package ejercicio11;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 11: Reproductor - Cancion - Artista ===\n");

        Artista artista = new Artista("Soda Stereo", "Rock");
        Cancion c1 = new Cancion("De música ligera", artista);
        Cancion c2 = new Cancion("Persiana americana", artista);

        Reproductor reproductor = new Reproductor();
        reproductor.reproducir(c1);
        reproductor.reproducir(c2);
    }
}
