package ejercicio14;

public class Render {
    private String formato;
    private Proyecto proyecto;

    public Render(String formato, Proyecto proyecto) {
        this.formato = formato;
        this.proyecto = proyecto;
    }

    public String getFormato() { return formato; }
    public Proyecto getProyecto() { return proyecto; }

    @Override
    public String toString() {
        return "Render " + formato + " de " + proyecto;
    }
}
