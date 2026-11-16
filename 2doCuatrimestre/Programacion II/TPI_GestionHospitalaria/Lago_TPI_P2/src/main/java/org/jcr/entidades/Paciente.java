package org.jcr.entidades;

import org.jcr.enums.TipoSangre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Paciente extends Persona {

    private final HistoriaClinica medicalRecord;
    private final String          phone;
    private final String          address;
    private Hospital              hospital;
    private final List<Cita>      appointments = new ArrayList<>();

    public Paciente(String firstName, String lastName,
                    String nationalId, LocalDate birthDate,
                    TipoSangre bloodType, String phone,
                    String address) {
        super(firstName, lastName, nationalId, birthDate, bloodType);
        this.phone   = Validations.requireText(phone,
                "El teléfono es obligatorio");
        this.address = Validations.requireText(address,
                "La dirección es obligatoria");
        // Composición: la historia nace con el paciente y no existe
        // sin él.
        this.medicalRecord = new HistoriaClinica(this);
    }

    public HistoriaClinica getHistoriaClinica() {
        return medicalRecord;
    }

    public String getTelefono() {
        return phone;
    }

    public String getDireccion() {
        return address;
    }

    public Hospital getHospital() {
        return hospital;
    }

    // Este es el único método que toca las dos puntas de la
    // relación; Hospital.agregarPaciente() delega acá para que no
    // queden desincronizadas.
    public void setHospital(Hospital newHospital) {
        if (hospital == newHospital) {
            return;
        }
        if (hospital != null) {
            hospital.internalPatients().remove(this);
        }
        hospital = newHospital;
        if (newHospital != null
                && !newHospital.internalPatients().contains(this)) {
            newHospital.internalPatients().add(this);
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
