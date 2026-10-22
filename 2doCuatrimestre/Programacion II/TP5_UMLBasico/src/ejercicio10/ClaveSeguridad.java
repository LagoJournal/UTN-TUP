package ejercicio10;

public class ClaveSeguridad {
    private String codigo;
    private String ultimaModificacion;

    public ClaveSeguridad(String codigo, String ultimaModificacion) {
        this.codigo = codigo;
        this.ultimaModificacion = ultimaModificacion;
    }

    public String getCodigo() { return codigo; }
    public String getUltimaModificacion() { return ultimaModificacion; }

    public void cambiarCodigo(String nuevoCodigo, String fecha) {
        if (nuevoCodigo == null || nuevoCodigo.length() < 4) {
            System.out.println("Error: la clave debe tener al menos 4 caracteres.");
            return;
        }
        this.codigo = nuevoCodigo;
        this.ultimaModificacion = fecha;
    }

    @Override
    public String toString() {
        return "****" + " (modificada: " + ultimaModificacion + ")";
    }
}
