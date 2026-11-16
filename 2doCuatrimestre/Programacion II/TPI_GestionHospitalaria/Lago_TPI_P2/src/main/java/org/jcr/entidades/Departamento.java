package org.jcr.entidades;

import org.jcr.enums.EspecialidadMedica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Departamento {

    private final String             name;
    private final EspecialidadMedica specialty;
    private Hospital                 hospital;
    private final List<Medico>       doctors = new ArrayList<>();
    private final List<Sala>         rooms   = new ArrayList<>();

    public Departamento(String name, EspecialidadMedica specialty) {
        this.name = Validations.requireText(name,
                "El nombre del departamento es obligatorio");
        Validations.requireNonNull(specialty,
                "La especialidad del departamento es obligatoria");
        this.specialty = specialty;
    }

    public String getNombre() {
        return name;
    }

    public EspecialidadMedica getEspecialidad() {
        return specialty;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital newHospital) {
        if (hospital == newHospital) {
            return;
        }
        if (hospital != null) {
            hospital.internalDepartments().remove(this);
        }
        hospital = newHospital;
        if (newHospital != null
                && !newHospital.internalDepartments().contains(this)) {
            newHospital.internalDepartments().add(this);
        }
    }

    // Delegamos en Medico, que además controla la especialidad.
    public void agregarMedico(Medico doctor) {
        Validations.requireNonNull(doctor, "El médico es obligatorio");
        doctor.setDepartamento(this);
    }

    // Composición: la sala solo existe dentro de su departamento.
    public Sala crearSala(String number, String type) {
        Sala room = new Sala(number, type, this);
        rooms.add(room);
        return room;
    }

    public List<Medico> getMedicos() {
        return Collections.unmodifiableList(doctors);
    }

    public List<Sala> getSalas() {
        return Collections.unmodifiableList(rooms);
    }

    List<Medico> internalDoctors() {
        return doctors;
    }
}
