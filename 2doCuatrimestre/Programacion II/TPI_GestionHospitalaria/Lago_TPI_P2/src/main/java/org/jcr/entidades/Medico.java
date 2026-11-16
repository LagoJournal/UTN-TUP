package org.jcr.entidades;

import org.jcr.enums.EspecialidadMedica;
import org.jcr.enums.TipoSangre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Medico extends Persona {

    private final Matricula          license;
    private final EspecialidadMedica specialty;
    private Departamento             department;
    private final List<Cita>         appointments = new ArrayList<>();

    public Medico(String firstName, String lastName,
                  String nationalId, LocalDate birthDate,
                  TipoSangre bloodType, String licenseNumber,
                  EspecialidadMedica specialty) {
        super(firstName, lastName, nationalId, birthDate, bloodType);
        // Composición: la matrícula la crea el médico y valida su
        // propio formato.
        this.license = new Matricula(licenseNumber);
        Validations.requireNonNull(specialty,
                "La especialidad es obligatoria");
        this.specialty = specialty;
    }

    public Matricula getMatricula() {
        return license;
    }

    public EspecialidadMedica getEspecialidad() {
        return specialty;
    }

    public Departamento getDepartamento() {
        return department;
    }

    public void setDepartamento(Departamento newDepartment) {
        // Rechazamos antes de modificar nada, así un médico rechazado
        // conserva su departamento anterior.
        if (newDepartment != null
                && newDepartment.getEspecialidad() != specialty) {
            throw new IllegalArgumentException(
                    "El médico no es de la especialidad del "
                    + "departamento");
        }
        if (department == newDepartment) {
            return;
        }
        if (department != null) {
            department.internalDoctors().remove(this);
        }
        department = newDepartment;
        if (newDepartment != null
                && !newDepartment.internalDoctors().contains(this)) {
            newDepartment.internalDoctors().add(this);
        }
    }

    public void addCita(Cita appointment) {
        if (appointment != null
                && !appointments.contains(appointment)) {
            appointments.add(appointment);
        }
    }

    public List<Cita> getCitas() {
        return Collections.unmodifiableList(appointments);
    }
}
