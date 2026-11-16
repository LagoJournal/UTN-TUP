package EjercicioTres;

public class EmpleadoTemporal extends Empleado {

    private int horasTrabajadas;
    private double valorHora;

    public EmpleadoTemporal(String nombre, double sueldoBase, int horasTrabajadas, double valorHora) {
        super(nombre, sueldoBase);
        this.horasTrabajadas = horasTrabajadas;
        this.valorHora = valorHora;
    }

    // El temporal cobra el básico más las horas trabajadas
    @Override
    public double calcularSueldo() {
        return sueldoBase + horasTrabajadas * valorHora;
    }
}
