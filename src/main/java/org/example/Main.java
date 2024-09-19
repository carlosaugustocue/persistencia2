package org.example;

import org.example.aplication.CitaService;
import org.example.aplication.PacienteService;
import org.example.domain.Cita;
import org.example.domain.Paciente;
import org.example.infrastructure.CitaRepository;
import org.example.infrastructure.IDManager;
import org.example.infrastructure.PacienteRepository;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PacienteRepository pacienteRepository = new PacienteRepository();
        PacienteService pacienteService = new PacienteService(pacienteRepository);

        CitaRepository citaRepository = new CitaRepository();
        CitaService citaService = new CitaService(citaRepository);

        boolean salir = false;

        while (!salir) {
            String[] opciones = {"Registrar nuevo paciente", "Actualizar paciente", "Registrar nueva cita", "Eliminar cita", "Mostrar pacientes", "Mostrar citas por paciente", "Salir"};
            String opcion = (String) JOptionPane.showInputDialog(null, "Selecciona una opción", "Menú principal", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            if (opcion == null) continue;

            switch (opcion) {
                case "Registrar nuevo paciente":
                    int nuevoIdPaciente = IDManager.obtenerUltimoId() + 1;  // Obtener el nuevo ID para el paciente

                    String nombre = JOptionPane.showInputDialog("Nombre:");
                    String apellido = JOptionPane.showInputDialog("Apellido:");
                    int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
                    String genero = JOptionPane.showInputDialog("Género:");
                    String direccion = JOptionPane.showInputDialog("Dirección:");
                    String telefono = JOptionPane.showInputDialog("Teléfono:");

                    Paciente nuevoPaciente = new Paciente(nuevoIdPaciente, nombre, apellido, edad, genero, direccion, telefono);
                    pacienteService.registrarPaciente(nuevoPaciente);
                    IDManager.actualizarUltimoId(nuevoIdPaciente);  // Actualizar el último ID en el archivo

                    JOptionPane.showMessageDialog(null, "Paciente registrado con éxito");
                    break;

                case "Actualizar paciente":
                    List<Paciente> pacientes = pacienteService.obtenerPacientes();
                    if (pacientes.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay pacientes registrados.");
                        break;
                    }
                    StringBuilder listaPacientes = new StringBuilder("Seleccione el ID del paciente:\n");
                    for (Paciente p : pacientes) {
                        listaPacientes.append(p.getId()).append(" - ").append(p.getNombre()).append(" ").append(p.getApellido()).append("\n");
                    }
                    int idPacienteActualizar = Integer.parseInt(JOptionPane.showInputDialog(listaPacientes.toString()));
                    Paciente pacienteActualizar = pacientes.stream().filter(p -> p.getId() == idPacienteActualizar).findFirst().orElse(null);
                    if (pacienteActualizar != null) {
                        nombre = JOptionPane.showInputDialog("Nombre:", pacienteActualizar.getNombre());
                        apellido = JOptionPane.showInputDialog("Apellido:", pacienteActualizar.getApellido());
                        edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:", pacienteActualizar.getEdad()));
                        genero = JOptionPane.showInputDialog("Género:", pacienteActualizar.getGenero());
                        direccion = JOptionPane.showInputDialog("Dirección:", pacienteActualizar.getDireccion());
                        telefono = JOptionPane.showInputDialog("Teléfono:", pacienteActualizar.getTelefono());

                        pacienteActualizar.setNombre(nombre);
                        pacienteActualizar.setApellido(apellido);
                        pacienteActualizar.setEdad(edad);
                        pacienteActualizar.setGenero(genero);
                        pacienteActualizar.setDireccion(direccion);
                        pacienteActualizar.setTelefono(telefono);

                        pacienteService.actualizarPaciente(pacienteActualizar);
                        JOptionPane.showMessageDialog(null, "Paciente actualizado con éxito");
                    } else {
                        JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
                    }
                    break;

                case "Registrar nueva cita":
                    pacientes = pacienteService.obtenerPacientes();
                    if (pacientes.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay pacientes registrados.");
                        break;
                    }
                    StringBuilder listaPacientesCita = new StringBuilder("Seleccione el ID del paciente:\n");
                    for (Paciente p : pacientes) {
                        listaPacientesCita.append(p.getId()).append(" - ").append(p.getNombre()).append(" ").append(p.getApellido()).append("\n");
                    }
                    int idPacienteCita = Integer.parseInt(JOptionPane.showInputDialog(listaPacientesCita.toString()));
                    Paciente pacienteCita = pacientes.stream().filter(p -> p.getId() == idPacienteCita).findFirst().orElse(null);
                    if (pacienteCita != null) {
                        int nuevoIdCita = IDManager.obtenerUltimoId() + 1;  // Obtener el nuevo ID para la cita

                        String fechaStr = JOptionPane.showInputDialog("Fecha de la cita (formato: yyyy-MM-dd HH:mm):");
                        LocalDateTime fechaHora = LocalDateTime.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                        String motivo = JOptionPane.showInputDialog("Motivo de la cita:");

                        Cita nuevaCita = new Cita(nuevoIdCita, fechaHora, motivo, pacienteCita);
                        citaService.registrarCita(nuevaCita);
                        IDManager.actualizarUltimoId(nuevoIdCita);  // Actualizar el último ID en el archivo

                        JOptionPane.showMessageDialog(null, "Cita registrada con éxito");
                    } else {
                        JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
                    }
                    break;

                case "Eliminar cita":
                    pacientes = pacienteService.obtenerPacientes();
                    if (pacientes.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay pacientes registrados.");
                        break;
                    }
                    StringBuilder listaPacientesEliminarCita = new StringBuilder("Seleccione el ID del paciente:\n");
                    for (Paciente p : pacientes) {
                        listaPacientesEliminarCita.append(p.getId()).append(" - ").append(p.getNombre()).append(" ").append(p.getApellido()).append("\n");
                    }
                    int idPacienteEliminarCita = Integer.parseInt(JOptionPane.showInputDialog(listaPacientesEliminarCita.toString()));
                    Paciente pacienteEliminarCita = pacientes.stream().filter(p -> p.getId() == idPacienteEliminarCita).findFirst().orElse(null);
                    if (pacienteEliminarCita != null) {
                        List<Cita> citasPaciente = citaService.obtenerCitasPorPaciente(pacienteEliminarCita);
                        if (citasPaciente.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No hay citas registradas para este paciente.");
                            break;
                        }
                        StringBuilder listaCitas = new StringBuilder("Seleccione el ID de la cita a eliminar:\n");
                        for (Cita c : citasPaciente) {
                            listaCitas.append(c.getId()).append(" - ").append(c.getMotivo()).append(" - ").append(c.getFechaHora()).append("\n");
                        }
                        int idCitaEliminar = Integer.parseInt(JOptionPane.showInputDialog(listaCitas.toString()));
                        Cita citaEliminar = citasPaciente.stream().filter(c -> c.getId() == idCitaEliminar).findFirst().orElse(null);
                        if (citaEliminar != null) {
                            citaService.eliminarCita(citaEliminar);
                            JOptionPane.showMessageDialog(null, "Cita eliminada con éxito");
                        } else {
                            JOptionPane.showMessageDialog(null, "Cita no encontrada.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
                    }
                    break;

                case "Mostrar pacientes":
                    StringBuilder pacientesList = new StringBuilder();
                    for (Paciente p : pacienteService.obtenerPacientes()) {
                        pacientesList.append(p).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, pacientesList.toString());
                    break;

                case "Mostrar citas por paciente":
                    pacientes = pacienteService.obtenerPacientes();
                    if (pacientes.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay pacientes registrados.");
                        break;
                    }
                    StringBuilder listaPacientesMostrarCitas = new StringBuilder("Seleccione el ID del paciente:\n");
                    for (Paciente p : pacientes) {
                        listaPacientesMostrarCitas.append(p.getId()).append(" - ").append(p.getNombre()).append(" ").append(p.getApellido()).append("\n");
                    }
                    int idPacienteMostrarCitas = Integer.parseInt(JOptionPane.showInputDialog(listaPacientesMostrarCitas.toString()));
                    Paciente pacienteMostrarCitas = pacientes.stream().filter(p -> p.getId() == idPacienteMostrarCitas).findFirst().orElse(null);
                    if (pacienteMostrarCitas != null) {
                        List<Cita> citas = citaService.obtenerCitasPorPaciente(pacienteMostrarCitas);
                        StringBuilder citasList = new StringBuilder();
                        for (Cita c : citas) {
                            citasList.append(c).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, citasList.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
                    }
                    break;

                case "Salir":
                    salir = true;
                    break;
            }
        }
    }
}
