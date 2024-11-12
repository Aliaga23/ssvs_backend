package com.ssvs.SSVS.backend.model;

public class Cupo {
    private int id;
    private int horarioMedicoEspecialidadId;
    private String horaInicio;
    private String horaFin;
    private String estado;  // Nuevo campo

    // Constructor actualizado para incluir estado
    public Cupo(int id, int horarioMedicoEspecialidadId, String horaInicio, String horaFin, String estado) {
        this.id = id;
        this.horarioMedicoEspecialidadId = horarioMedicoEspecialidadId;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHorarioMedicoEspecialidadId() {
        return horarioMedicoEspecialidadId;
    }

    public void setHorarioMedicoEspecialidadId(int horarioMedicoEspecialidadId) {
        this.horarioMedicoEspecialidadId = horarioMedicoEspecialidadId;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
