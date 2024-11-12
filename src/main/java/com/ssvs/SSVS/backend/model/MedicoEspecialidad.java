// src/main/java/com/ssvs/SSVS/backend/model/MedicoEspecialidad.java
package com.ssvs.SSVS.backend.model;

public class MedicoEspecialidad {
    private int medicoId;
    private String medicoNombre;
    private String medicoApellido;
    private int especialidadId;
    private String especialidadNombre;

    // Getters y Setters
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
}
