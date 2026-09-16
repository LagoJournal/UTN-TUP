package Ejercicio_01;

/**
 * Prueba la clase Empleado: instanciación con ambos constructores,
 * actualización de salario con datos válidos e inválidos, y conteo
 * estático de empleados creados.
 *
 * @author agustinlago
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Caso 1: Sistema de Gestión de Empleados ===\n");

        Empleado emp1 = new Empleado("Agustín Lago", "Desarrollador");
        Empleado emp2 = new Empleado(50, "Luis Pérez", "Diseñador", 3000.0);
        Empleado emp3 = new Empleado("María Torres", "RRHH");

        System.out.println("--- Actualizaciones válidas ---");
        emp1.actualizarSalario(10.0);   // porcentaje
        emp2.actualizarSalario(2000);   // monto fijo

        System.out.println("\n--- Probando robustez con datos inválidos ---");
        emp1.actualizarSalario(-5.0);   // porcentaje negativo, debe rechazarse
        emp2.actualizarSalario(-100);   // monto fijo negativo, debe rechazarse
        emp3.setSalario(-999);          // setter directo con salario negativo

        System.out.println("\n--- Estado final ---");
        System.out.println(emp1);
        System.out.println(emp2);
        System.out.println(emp3);

        System.out.println("\nTotal de empleados creados: " + Empleado.mostrarTotalEmpleados());
    }
}
