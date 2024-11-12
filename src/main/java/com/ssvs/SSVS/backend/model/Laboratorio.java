package com.ssvs.SSVS.backend.model;

import java.time.LocalDateTime;

public class Laboratorio {

    private int id;
    private int consultaId;
    private String tipoAnalisis;
    private String descripcion;
    private LocalDateTime fechaProgramacion;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConsultaId() {
        return consultaId;
    }

    public void setConsultaId(int consultaId) {
        this.consultaId = consultaId;
    }

    public String getTipoAnalisis() {
        return tipoAnalisis;
    }

    public void setTipoAnalisis(String tipoAnalisis) {
        this.tipoAnalisis = tipoAnalisis;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaProgramacion() {
        return fechaProgramacion;
    }

    public void setFechaProgramacion(LocalDateTime fechaProgramacion) {
        this.fechaProgramacion = fechaProgramacion;
    }
}
