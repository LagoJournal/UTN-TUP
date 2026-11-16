package org.jcr.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sala {

    private final String       number;
    private final String       type;
    private final Departamento department;
    private final List<Cita>   appointments = new ArrayList<>();

    // Constructor de paquete: solo Departamento.crearSala() crea salas.
    Sala(String number, String type, Departamento department) {
        this.number = Validations.requireText(number,
                "El número de sala es obligatorio");
        this.type   = Validations.requireText(type,
                "El tipo de sala es obligatorio");
        this.department = department;
    }

    public String getNumero() {
        return number;
    }

    public String getTipo() {
        return type;
    }

    public Departamento getDepartamento() {
        return department;
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
