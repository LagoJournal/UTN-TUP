package EjercicioDos;

public class Rectangulo extends Figura {

    private double base;
    private double altura;

    public Rectangulo(double base, double altura) {
        super("Rectángulo");
        this.base = base;
        this.altura = altura;
    }

    // Área del rectángulo: base * altura
    @Override
    public double calcularArea() {
        return base * altura;
    }
}
