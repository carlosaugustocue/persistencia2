package org.example.infrastructure;



import org.example.domain.Cita;
import org.example.domain.Paciente;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CitaRepository {
    private static final String FILE_PATH = "citas.txt";
    private PacienteRepository pacienteRepository;  // Necesitamos el repositorio de pacientes para asociar

    public CitaRepository() {
        this.pacienteRepository = new PacienteRepository();  // Inicializamos el repositorio de pacientes
    }

    // Método para guardar citas
    public void guardar(Cita cita) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(cita.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar citas
    public void eliminar(Cita cita) {
        List<Cita> citas = cargarCitas();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Cita c : citas) {
                if (c.getId() != cita.getId()) {
                    writer.write(c.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar citas desde el archivo
    public List<Cita> cargarCitas() {
        List<Cita> citas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                // Asegúrate de leer todos los campos
                int id = Integer.parseInt(data[0]);
                LocalDateTime fechaHora = LocalDateTime.parse(data[1], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
                String motivo = data[2];
                int idPaciente = Integer.parseInt(data[3]);

                // Busca al paciente por su ID
                Paciente paciente = pacienteRepository.obtenerPacientePorId(idPaciente);

                if (paciente != null) {
                    Cita cita = new Cita(id, fechaHora, motivo, paciente);  // Constructor completo
                    citas.add(cita);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return citas;
    }

    // Método para obtener citas de un paciente específico
    public List<Cita> obtenerCitasPorPaciente(Paciente paciente) {
        List<Cita> citasPorPaciente = new ArrayList<>();
        List<Cita> citas = cargarCitas();
        for (Cita c : citas) {
            if (c.getPaciente().getId() == paciente.getId()) {
                citasPorPaciente.add(c);
            }
        }
        return citasPorPaciente;
    }
}
