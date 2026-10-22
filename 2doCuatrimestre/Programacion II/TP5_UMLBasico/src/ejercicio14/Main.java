package ejercicio14;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 14: EditorVideo - Proyecto - Render ===\n");

        Proyecto proyecto = new Proyecto("Corto_TUP", 12);
        EditorVideo editor = new EditorVideo();

        editor.exportar("MP4", proyecto);
        editor.exportar("MOV", proyecto);
    }
}
