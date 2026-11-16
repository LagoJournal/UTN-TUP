package org.jcr.enums;

public enum EstadoCita {
    PROGRAMADA("Programada"),
    EN_CURSO("En Curso"),
    COMPLETADA("Completada"),
    CANCELADA("Cancelada"),
    NO_ASISTIO("No Asistió");

    private final String description;

    EstadoCita(String description) {
        this.description = description;
    }

    public String getDescripcion() {
        return description;
    }
}
