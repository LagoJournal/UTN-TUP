package org.jcr.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hospital {

    private final String             name;
    private final String             address;
    private final String             phone;
    private final List<Departamento> departments = new ArrayList<>();
    private final List<Paciente>     patients    = new ArrayList<>();

    public Hospital(String name, String address, String phone) {
        this.name    = Validations.requireText(name,
                "El nombre del hospital es obligatorio");
        this.address = Validations.requireText(address,
                "La dirección del hospital es obligatoria");
        this.phone   = Validations.requireText(phone,
                "El teléfono del hospital es obligatorio");
    }

    public String getNombre() {
        return name;
    }

    public String getDireccion() {
        return address;
    }

    public String getTelefono() {
        return phone;
    }

    // Delegamos en la otra punta para que un solo método mantenga
    // sincronizadas las dos listas.
    public void agregarDepartamento(Departamento department) {
        Validations.requireNonNull(department,
                "El departamento es obligatorio");
        department.setHospital(this);
    }

    public void agregarPaciente(Paciente patient) {
        Validations.requireNonNull(patient,
                "El paciente es obligatorio");
        patient.setHospital(this);
    }

    public List<Departamento> getDepartamentos() {
        return Collections.unmodifiableList(departments);
    }

    public List<Paciente> getPacientes() {
        return Collections.unmodifiableList(patients);
    }

    // Acceso de paquete a las listas reales: solo las otras puntas
    // de la relación pueden modificarlas.
    List<Departamento> internalDepartments() {
        return departments;
    }

    List<Paciente> internalPatients() {
        return patients;
    }
}
