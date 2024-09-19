package org.example.infrastructure;

import org.example.domain.Paciente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepository {
    private static final String FILE_PATH = "pacientes.txt";

    // Método para guardar un paciente
    public void guardar(Paciente paciente) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(paciente.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar un paciente
    public void actualizar(Paciente paciente) {
        List<Paciente> pacientes = cargarPacientes();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Paciente p : pacientes) {
                if (p.getId() == paciente.getId()) {
                    writer.write(paciente.toString());
                } else {
                    writer.write(p.toString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar todos los pacientes desde el archivo
    public List<Paciente> cargarPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String nombre = data[1];
                String apellido = data[2];
                int edad = Integer.parseInt(data[3]);
                String genero = data[4];
                String direccion = data[5];
                String telefono = data[6];
                Paciente paciente = new Paciente(id, nombre, apellido, edad, genero, direccion, telefono);
                pacientes.add(paciente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    // Método para obtener un paciente por su ID
    public Paciente obtenerPacientePorId(int id) {
        List<Paciente> pacientes = cargarPacientes();
        for (Paciente paciente : pacientes) {
            if (paciente.getId() == id) {
                return paciente;  // Devuelve el paciente si encuentra el ID
            }
        }
        return null;  // Devuelve null si no se encuentra el paciente
    }
}
