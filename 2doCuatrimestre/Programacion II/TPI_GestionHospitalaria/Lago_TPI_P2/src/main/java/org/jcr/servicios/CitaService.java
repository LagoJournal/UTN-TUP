package org.jcr.servicios;

import org.jcr.entidades.Cita;
import org.jcr.entidades.Medico;
import org.jcr.entidades.Paciente;
import org.jcr.entidades.Sala;
import org.jcr.excepciones.CitaException;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaService {

    Cita programarCita(Paciente patient, Medico doctor, Sala room,
                       LocalDateTime dateTime, double cost)
            throws CitaException;

    List<Cita> getCitasPorPaciente(Paciente patient);

    List<Cita> getCitasPorMedico(Medico doctor);

    List<Cita> getCitasPorSala(Sala room);
}
