package com.ssvs.SSVS.backend.model;

import java.time.LocalTime;

public class Horario {
    private int id;
    private int diaId;
    private int jornadaId;

    // Campos adicionales para representar detalles del día y jornada
    private String diaSemana;
    private String jornadaNombre;
    private LocalTime horaInicio;
    private LocalTime horaFinal;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiaId() {
        return diaId;
    }

    public void setDiaId(int diaId) {
        this.diaId = diaId;
    }

    public int getJornadaId() {
        return jornadaId;
    }

    public void setJornadaId(int jornadaId) {
        this.jornadaId = jornadaId;
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

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }
}
