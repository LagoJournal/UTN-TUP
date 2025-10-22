/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tp4_poo2;

/**
 *
 * @author agustinlago
 */
public class TP4_POO2 {

    public static void main(String[] args) {

        Empleado emp1 = new Empleado("Agustín Lago", "Desarrollador");
        Empleado emp2 = new Empleado(3,"Luis Pérez", "Diseñador",3000);
        Empleado emp3 = new Empleado("María Torez", "RRHH");
            
        emp1.actualizarSalario(10.00); 
        emp2.actualizarSalario(2000); 

        System.out.println(emp1);
        System.out.println(emp2);
        System.out.println(emp3);

        System.out.println("\nTotal de empleados: " + Empleado.mostrarTotalEmpleados());

        
    }
    
}
