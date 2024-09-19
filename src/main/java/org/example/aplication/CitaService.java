package org.example.aplication;



import org.example.domain.Cita;
import org.example.domain.Paciente;
import org.example.infrastructure.CitaRepository;

import java.util.List;

public class CitaService {
    private CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public void registrarCita(Cita cita) {
        citaRepository.guardar(cita);
    }

    public void eliminarCita(Cita cita) {
        citaRepository.eliminar(cita);
    }

    public List<Cita> obtenerCitasPorPaciente(Paciente paciente) {
        return citaRepository.obtenerCitasPorPaciente(paciente);
    }
}

