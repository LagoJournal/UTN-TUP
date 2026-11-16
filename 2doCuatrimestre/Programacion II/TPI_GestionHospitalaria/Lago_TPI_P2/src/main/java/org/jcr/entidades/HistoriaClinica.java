package org.jcr.entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoriaClinica {

    private final String        recordNumber;
    private final Paciente      patient;
    private final LocalDateTime createdAt;
    private final List<String>  diagnoses  = new ArrayList<>();
    private final List<String>  treatments = new ArrayList<>();
    private final List<String>  allergies  = new ArrayList<>();

    // Constructor de paquete: solo Paciente puede crear su historia.
    HistoriaClinica(Paciente patient) {
        this.patient      = patient;
        this.createdAt    = LocalDateTime.now();
        this.recordNumber = buildRecordNumber();
    }

    public String getNumeroHistoria() {
        return recordNumber;
    }

    public Paciente getPaciente() {
        return patient;
    }

    public void agregarDiagnostico(String diagnosis) {
        addIfNotBlank(diagnoses, diagnosis);
    }

    public void agregarTratamiento(String treatment) {
        addIfNotBlank(treatments, treatment);
    }

    public void agregarAlergia(String allergy) {
        addIfNotBlank(allergies, allergy);
    }

    public List<String> getDiagnosticos() {
        return Collections.unmodifiableList(diagnoses);
    }

    public List<String> getTratamientos() {
        return Collections.unmodifiableList(treatments);
    }

    public List<String> getAlergias() {
        return Collections.unmodifiableList(allergies);
    }

    // Usamos el año de creación y no el actual para que el número no
    // cambie con el paso del tiempo.
    private String buildRecordNumber() {
        return "HC-" + patient.getDni() + "-" + createdAt.getYear();
    }

    // La consigna pide ignorar los textos vacíos en lugar de lanzar
    // una excepción.
    private void addIfNotBlank(List<String> list, String text) {
        if (text != null && !text.trim().isEmpty()) {
            list.add(text);
        }
    }
}
