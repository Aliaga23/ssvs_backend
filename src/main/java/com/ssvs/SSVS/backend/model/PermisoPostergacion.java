package com.ssvs.SSVS.backend.model;

import java.sql.Time;
import java.util.Date;

public class PermisoPostergacion {
    private int postergacionId;
    private int medicoId;
    private String tipoPostergacion;
    private Date fechaSolicitud;
    private Date fechaInicio;
    private Time horaInicio;
    private Time horaFin;
    private String estado;
    private String motivo;

    // Getters y Setters
    public int getPostergacionId() {
        return postergacionId;
    }

    public void setPostergacionId(int postergacionId) {
        this.postergacionId = postergacionId;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }

    public String getTipoPostergacion() {
        return tipoPostergacion;
    }

    public void setTipoPostergacion(String tipoPostergacion) {
        this.tipoPostergacion = tipoPostergacion;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
