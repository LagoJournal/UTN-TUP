package EjercicioUno;

// Clase base de la jerarquía de vehículos
public class Vehiculo {

    // Atributos protected: visibles desde las subclases
    protected String marca;
    protected String modelo;

    public Vehiculo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    // Mostramos los datos comunes a todo vehículo
    public void mostrarInfo() {
        System.out.println("Marca: " + marca + " | Modelo: " + modelo);
    }
}
