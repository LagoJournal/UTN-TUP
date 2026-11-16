package EjercicioUno;

// Auto es un Vehiculo (relación is-a)
public class Auto extends Vehiculo {

    private int cantidadPuertas;

    // Inicializamos los atributos heredados con super(...)
    public Auto(String marca, String modelo, int cantidadPuertas) {
        super(marca, modelo);
        this.cantidadPuertas = cantidadPuertas;
    }

    // Reutilizamos la versión de la superclase y agregamos el dato propio
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Cantidad de puertas: " + cantidadPuertas);
    }
}
