package EjercicioTres;

import java.util.ArrayList;

// Kata 3: sueldo polimórfico, instanceof y downcasting seguro
public class Demo {

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 3: Empleados ===");
        ArrayList<Empleado> empleados = new ArrayList<>();
        empleados.add(new EmpleadoPlanta("Ana", 500000, 4));
        empleados.add(new EmpleadoTemporal("Luis", 200000, 80, 3000));
        empleados.add(new EmpleadoPlanta("Marta", 600000, 10));

        int cantPlanta = 0;
        int cantTemporal = 0;

        for (Empleado e : empleados) {
            System.out.printf("%s -> sueldo: $%.2f%n", e.getNombre(), e.calcularSueldo());

            // Verificamos el tipo real antes de convertir la referencia
            if (e instanceof EmpleadoPlanta) {
                EmpleadoPlanta planta = (EmpleadoPlanta) e;
                System.out.println("   Tipo: planta | Antigüedad: " + planta.getAntiguedad() + " años");
                cantPlanta++;
            } else if (e instanceof EmpleadoTemporal) {
                System.out.println("   Tipo: temporal");
                cantTemporal++;
            }
        }
        System.out.println("Total planta: " + cantPlanta + " | Total temporales: " + cantTemporal);
    }
}
