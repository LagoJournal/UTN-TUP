package ejercicio2;

public class Bateria {
    private String modelo;
    private int capacidad;

    public Bateria(String modelo, int capacidad) {
        this.modelo = modelo;
        this.capacidad = capacidad;
    }

    public String getModelo() { return modelo; }
    public int getCapacidad() { return capacidad; }

    @Override
    public String toString() {
        return modelo + " (" + capacidad + " mAh)";
    }
}
