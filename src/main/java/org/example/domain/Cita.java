package org.example.domain;

import java.time.LocalDateTime;

public class Cita {
    private int id;
    private LocalDateTime fechaHora;
    private String motivo;
    private Paciente paciente;

    public Cita(int id, LocalDateTime fechaHora, String motivo, Paciente paciente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.paciente = paciente;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return id + "," + fechaHora + "," + motivo + "," + paciente.getId();
    }
}
