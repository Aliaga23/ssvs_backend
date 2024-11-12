package com.ssvs.SSVS.backend.model;

import java.time.LocalTime;

public class HorarioMedicoEspecialidad {
    private int horarioMedicoEspecialidadId;
    private int medicoId;
    private String medicoNombre;
    private String medicoApellido;
    private int especialidadId;
    private String especialidadNombre;
    private int horarioId;
    private String diaSemana;
    private String jornadaNombre;
    private LocalTime horaInicio;  // Cambiado a LocalTime
    private LocalTime horaFin;     // Cambiado a LocalTime

    // Getters y Setters para cada atributo
    public int getHorarioMedicoEspecialidadId() {
        return horarioMedicoEspecialidadId;
    }

    public void setHorarioMedicoEspecialidadId(int horarioMedicoEspecialidadId) {
        this.horarioMedicoEspecialidadId = horarioMedicoEspecialidadId;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }

    public String getMedicoNombre() {
        return medicoNombre;
    }

    public void setMedicoNombre(String medicoNombre) {
        this.medicoNombre = medicoNombre;
    }

    public String getMedicoApellido() {
        return medicoApellido;
    }

    public void setMedicoApellido(String medicoApellido) {
        this.medicoApellido = medicoApellido;
    }

    public int getEspecialidadId() {
        return especialidadId;
    }

    public void setEspecialidadId(int especialidadId) {
        this.especialidadId = especialidadId;
    }

    public String getEspecialidadNombre() {
        return especialidadNombre;
    }

    public void setEspecialidadNombre(String especialidadNombre) {
        this.especialidadNombre = especialidadNombre;
    }

    public int getHorarioId() {
        return horarioId;
    }

    public void setHorarioId(int horarioId) {
        this.horarioId = horarioId;
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


}
