package ejercicio9;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 9: CitaMedica - Paciente - Profesional ===\n");

        Paciente paciente = new Paciente("Agustín Lago", "OSDE");
        Profesional profesional = new Profesional("Dra. Laura Gómez", "Cardiología");
        CitaMedica cita = new CitaMedica("2026-11-05", "10:30", paciente, profesional);

        System.out.println(cita);

        System.out.println("\n--- Navegación unidireccional ---");
        System.out.println("Paciente: " + cita.getPaciente().getNombre());
        System.out.println("Especialidad: " + cita.getProfesional().getEspecialidad());
    }
}
