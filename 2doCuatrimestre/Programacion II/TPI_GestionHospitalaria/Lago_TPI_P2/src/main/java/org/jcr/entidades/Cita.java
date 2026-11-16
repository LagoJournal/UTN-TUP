package org.jcr.entidades;

import org.jcr.enums.EstadoCita;

import java.time.LocalDateTime;

public class Cita {

    private final Paciente      patient;
    private final Medico        doctor;
    private final Sala          room;
    private final LocalDateTime dateTime;
    private final double        cost;
    private EstadoCita          status;
    private String              notes;

    public Cita(Paciente patient, Medico doctor, Sala room,
                LocalDateTime dateTime, double cost) {
        Validations.requireNonNull(patient,
                "La cita necesita un paciente");
        Validations.requireNonNull(doctor,
                "La cita necesita un médico");
        Validations.requireNonNull(room,
                "La cita necesita una sala");
        Validations.requireNonNull(dateTime,
                "La cita necesita fecha y hora");
        this.patient  = patient;
        this.doctor   = doctor;
        this.room     = room;
        this.dateTime = dateTime;
        this.cost     = cost;
        this.status   = EstadoCita.PROGRAMADA;
        this.notes    = "";
    }

    public Paciente getPaciente() {
        return patient;
    }

    public Medico getMedico() {
        return doctor;
    }

    public Sala getSala() {
        return room;
    }

    public LocalDateTime getFechaHora() {
        return dateTime;
    }

    public double getCosto() {
        return cost;
    }

    public EstadoCita getEstado() {
        return status;
    }

    // Validamos antes de asignar para no perder el estado anterior.
    public void setEstado(EstadoCita newStatus) {
        Validations.requireNonNull(newStatus,
                "El estado de la cita es obligatorio");
        status = newStatus;
    }

    public String getObservaciones() {
        return notes;
    }

    // Guardamos texto vacío en lugar de null para que quien lea las
    // observaciones no tenga que controlar nulos.
    public void setObservaciones(String newNotes) {
        notes = (newNotes == null) ? "" : newNotes;
    }
}
