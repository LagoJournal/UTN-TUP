package EjercicioDos;

public class Circulo extends Figura {

    private double radio;

    public Circulo(double radio) {
        super("Círculo");
        this.radio = radio;
    }

    // Área del círculo: PI * r^2
    @Override
    public double calcularArea() {
        return Math.PI * radio * radio;
    }
}
