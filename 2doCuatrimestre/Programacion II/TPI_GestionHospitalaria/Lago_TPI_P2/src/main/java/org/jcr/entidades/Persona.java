package org.jcr.entidades;

import org.jcr.enums.TipoSangre;

import java.time.LocalDate;

// Es abstracta porque en el hospital no existe una persona suelta:
// siempre es un paciente o un médico.
public abstract class Persona {

    private final String     firstName;
    private final String     lastName;
    private final String     nationalId;
    private final LocalDate  birthDate;
    private final TipoSangre bloodType;

    public Persona(String firstName, String lastName,
                   String nationalId, LocalDate birthDate,
                   TipoSangre bloodType) {
        // Validamos antes de asignar: con un dato inválido el objeto
        // no llega a existir.
        this.firstName  = Validations.requireText(firstName,
                "El nombre es obligatorio");
        this.lastName   = Validations.requireText(lastName,
                "El apellido es obligatorio");
        this.nationalId = validateNationalId(nationalId);
        Validations.requireNonNull(birthDate,
                "La fecha de nacimiento es obligatoria");
        Validations.requireNonNull(bloodType,
                "El tipo de sangre es obligatorio");
        this.birthDate  = birthDate;
        this.bloodType  = bloodType;
    }

    public String getNombre() {
        return firstName;
    }

    public String getApellido() {
        return lastName;
    }

    public String getDni() {
        return nationalId;
    }

    public LocalDate getFechaNacimiento() {
        return birthDate;
    }

    public TipoSangre getTipoSangre() {
        return bloodType;
    }

    public String getNombreCompleto() {
        return firstName + " " + lastName;
    }

    public int getEdad() {
        LocalDate today = LocalDate.now();
        int age = today.getYear() - birthDate.getYear();
        // Restar el año de nacimiento sobrestima la edad si el
        // cumpleaños de este año todavía no llegó.
        if (birthDate.plusYears(age).isAfter(today)) {
            age--;
        }
        return age;
    }

    private String validateNationalId(String nationalId) {
        if (nationalId == null
                || nationalId.length() < 7
                || nationalId.length() > 8
                || !Validations.isDigits(nationalId)) {
            throw new IllegalArgumentException(
                    "El DNI debe tener 7 u 8 dígitos");
        }
        return nationalId;
    }
}
