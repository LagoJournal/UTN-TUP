package ejercicio8;

public class Documento {
    private String titulo;
    private String contenido;
    private final FirmaDigital firma;

    public Documento(String titulo, String contenido, String codigoHash, String fecha, Usuario firmante) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.firma = new FirmaDigital(codigoHash, fecha, firmante);
    }

    public String getTitulo() { return titulo; }
    public String getContenido() { return contenido; }
    public FirmaDigital getFirma() { return firma; }

    @Override
    public String toString() {
        return "Documento \"" + titulo + "\": " + contenido + "\n  Firma: " + firma;
    }
}
