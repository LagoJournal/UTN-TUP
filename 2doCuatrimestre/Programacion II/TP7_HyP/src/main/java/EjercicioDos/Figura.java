package EjercicioDos;

// Clase abstracta: define la estructura base, no se instancia
public abstract class Figura {

    protected String nombre;

    public Figura(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // Cada subclase está obligada a implementar su propio cálculo
    public abstract double calcularArea();
}
