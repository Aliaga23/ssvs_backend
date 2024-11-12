package com.ssvs.SSVS.backend.dto;

import java.time.LocalTime;
public class CupoInfoDTO {
    private int cupoId;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private int diaId;
    private String diaSemana;
    private String jornadaNombre;
    private LocalTime jornadaInicio;
    private LocalTime jornadaFin;
    private int medicoId;
    private String nombreMedico;
    private String apellidoMedico;
    private int especialidadId;
    private String nombreEspecialidad;
    private int horarioMedicoEspecialidadId; // Nueva propiedad
    private boolean disponible; // Nuevo campo de disponibilidad
    private String estado;  // Nuevo campo

    // Getters y Setters para 'disponible'
    public boolean isDisponible() {
        return disponible;
    }
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    // Getters y Setters
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

    public int getDiaId() {
        return diaId;
    }

    public void setDiaId(int diaId) {
        this.diaId = diaId;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getJornadaNombre() {
        return jornadaNombre;
    }

    public void setJornadaNombre(String jornadaNombre) {
        this.jornadaNombre = jornadaNombre;
    }

    public LocalTime getJornadaInicio() {
        return jornadaInicio;
    }

    public void setJornadaInicio(LocalTime jornadaInicio) {
        this.jornadaInicio = jornadaInicio;
    }

    public LocalTime getJornadaFin() {
        return jornadaFin;
    }

    public void setJornadaFin(LocalTime jornadaFin) {
        this.jornadaFin = jornadaFin;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
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

    public int getEspecialidadId() {
        return especialidadId;
    }

    public void setEspecialidadId(int especialidadId) {
        this.especialidadId = especialidadId;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public int getHorarioMedicoEspecialidadId() {
        return horarioMedicoEspecialidadId;
    }

    public void setHorarioMedicoEspecialidadId(int horarioMedicoEspecialidadId) {
        this.horarioMedicoEspecialidadId = horarioMedicoEspecialidadId;
    }
}