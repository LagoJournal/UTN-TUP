package EjercicioTres;

public class EmpleadoPlanta extends Empleado {

    private int antiguedad;

    public EmpleadoPlanta(String nombre, double sueldoBase, int antiguedad) {
        super(nombre, sueldoBase);
        this.antiguedad = antiguedad;
    }

    // Método propio: no existe en Empleado, requiere downcasting para invocarlo
    public int getAntiguedad() {
        return antiguedad;
    }

    // Sumamos un 5% del sueldo base por cada año de antigüedad
    @Override
    public double calcularSueldo() {
        return sueldoBase + sueldoBase * 0.05 * antiguedad;
    }
}
