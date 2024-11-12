package com.ssvs.SSVS.backend.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {
    private int reservaId;                 // ID de la reserva
    private int pacienteId;                // ID del paciente
    private String nombrePaciente;         // Nombre del paciente
    private String apellidoPaciente;       // Apellido del paciente
    private String estado;                 // Estado de la reserva (e.g., Reservada, Cancelada)
    private LocalDate fechaReserva;        // Fecha de la reserva
    private int cupoId;                    // ID del cupo
    private LocalTime horaInicio;          // Hora de inicio del cupo
    private LocalTime horaFin;             // Hora de fin del cupo
    private String nombreMedico;           // Nombre del médico
    private String apellidoMedico;         // Apellido del médico
    private String especialidadMedico;     // Especialidad del médico

    // Getters y Setters
    public int getReservaId() {
        return reservaId;
    }

    public void setReservaId(int reservaId) {
        this.reservaId = reservaId;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getApellidoPaciente() {
        return apellidoPaciente;
    }

    public void setApellidoPaciente(String apellidoPaciente) {
        this.apellidoPaciente = apellidoPaciente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public int getCupoId() {
        return cupoId;
    }

    public void setCupoId(int cupoId) {
        this.cupoId = cupoId;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getApellidoMedico() {
        return apellidoMedico;
    }

    public void setApellidoMedico(String apellidoMedico) {
        this.apellidoMedico = apellidoMedico;
    }

    public String getEspecialidadMedico() {
        return especialidadMedico;
    }

    public void setEspecialidadMedico(String especialidadMedico) {
        this.especialidadMedico = especialidadMedico;
    }
}
