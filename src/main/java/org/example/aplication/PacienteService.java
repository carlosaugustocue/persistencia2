package org.example.aplication;


import org.example.domain.Paciente;
import org.example.infrastructure.PacienteRepository;
import java.util.List;

public class PacienteService {
    private PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public void registrarPaciente(Paciente paciente) {
        pacienteRepository.guardar(paciente);
    }

    public void actualizarPaciente(Paciente paciente) {
        pacienteRepository.actualizar(paciente);
    }

    public List<Paciente> obtenerPacientes() {
        return pacienteRepository.cargarPacientes();
    }
}

