package org.jcr.servicios;

import org.jcr.entidades.Cita;
import org.jcr.entidades.Medico;
import org.jcr.entidades.Paciente;
import org.jcr.entidades.Sala;
import org.jcr.enums.EstadoCita;
import org.jcr.excepciones.CitaException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CitaManager implements CitaService {

    private static final int HOURS_BETWEEN_APPOINTMENTS = 2;

    private final List<Cita> appointments = new ArrayList<>();

    @Override
    public Cita programarCita(Paciente patient, Medico doctor,
                              Sala room, LocalDateTime dateTime,
                              double cost) throws CitaException {
        // Hacemos todas las validaciones antes de crear la cita, así
        // un rechazo no deja registros a medias.
        if (patient == null || doctor == null || room == null) {
            throw new CitaException(
                    "La cita necesita paciente, médico y sala");
        }
        validateAppointment(dateTime, cost);
        if (room.getDepartamento().getEspecialidad()
                != doctor.getEspecialidad()) {
            throw new CitaException("La especialidad del médico no "
                    + "coincide con la de la sala");
        }
        if (!isFree(getCitasPorMedico(doctor), dateTime)) {
            throw new CitaException("El médico ya tiene una cita a "
                    + "menos de " + HOURS_BETWEEN_APPOINTMENTS
                    + " horas");
        }
        if (!isFree(getCitasPorSala(room), dateTime)) {
            throw new CitaException("La sala ya tiene una cita a "
                    + "menos de " + HOURS_BETWEEN_APPOINTMENTS
                    + " horas");
        }

        Cita appointment = new Cita(patient, doctor, room, dateTime,
                cost);
        appointments.add(appointment);
        patient.addCita(appointment);
        doctor.addCita(appointment);
        room.addCita(appointment);
        return appointment;
    }

    @Override
    public List<Cita> getCitasPorPaciente(Paciente patient) {
        List<Cita> result = new ArrayList<>();
        for (Cita appointment : appointments) {
            if (appointment.getPaciente() == patient) {
                result.add(appointment);
            }
        }
        return result;
    }

    @Override
    public List<Cita> getCitasPorMedico(Medico doctor) {
        List<Cita> result = new ArrayList<>();
        for (Cita appointment : appointments) {
            if (appointment.getMedico() == doctor) {
                result.add(appointment);
            }
        }
        return result;
    }

    @Override
    public List<Cita> getCitasPorSala(Sala room) {
        List<Cita> result = new ArrayList<>();
        for (Cita appointment : appointments) {
            if (appointment.getSala() == room) {
                result.add(appointment);
            }
        }
        return result;
    }

    private void validateAppointment(LocalDateTime dateTime,
                                     double cost)
            throws CitaException {
        if (dateTime == null
                || dateTime.isBefore(LocalDateTime.now())) {
            throw new CitaException(
                    "La fecha de la cita es nula o ya pasó");
        }
        if (cost <= 0) {
            throw new CitaException(
                    "El costo de la cita debe ser mayor a 0");
        }
    }

    // Usamos el mismo control para médico y sala. Las citas
    // canceladas no cuentan porque ya no ocupan el horario.
    private boolean isFree(List<Cita> taken, LocalDateTime dateTime) {
        for (Cita existing : taken) {
            if (existing.getEstado() != EstadoCita.CANCELADA
                    && overlap(existing.getFechaHora(), dateTime)) {
                return false;
            }
        }
        return true;
    }

    // Comparamos con isAfter/isBefore estrictos para que una
    // diferencia de exactamente 2 horas se acepte.
    private boolean overlap(LocalDateTime existing,
                            LocalDateTime candidate) {
        return candidate.isAfter(
                    existing.minusHours(HOURS_BETWEEN_APPOINTMENTS))
                && candidate.isBefore(
                    existing.plusHours(HOURS_BETWEEN_APPOINTMENTS));
    }
}
