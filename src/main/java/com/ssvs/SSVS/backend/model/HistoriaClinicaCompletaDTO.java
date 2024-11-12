// src/main/java/com/ssvs/SSVS/backend/model/HistoriaClinicaResumenDTO.java
package com.ssvs.SSVS.backend.model;

import java.time.LocalDateTime;

public class HistoriaClinicaCompletaDTO {
    private int pacienteId;
    private String nombrePaciente;
    private String apellidoPaciente;
    private int historiaId;
    private LocalDateTime fechaCreacionHistoria;
    private int consultaId;
    private LocalDateTime fechaConsulta;
    private String diagnostico;
    private String tratamiento;
    private String notasConsulta;
    private String medicamentosReceta;
    private String laboratorios;

    // Getters y Setters
    public int getPacienteId() { return pacienteId; }
    public void setPacienteId(int pacienteId) { this.pacienteId = pacienteId; }

    public String getNombrePaciente() { return nombrePaciente; }
    public void setNombrePaciente(String nombrePaciente) { this.nombrePaciente = nombrePaciente; }

    public String getApellidoPaciente() { return apellidoPaciente; }
    public void setApellidoPaciente(String apellidoPaciente) { this.apellidoPaciente = apellidoPaciente; }

    public int getHistoriaId() { return historiaId; }
    public void setHistoriaId(int historiaId) { this.historiaId = historiaId; }

    public LocalDateTime getFechaCreacionHistoria() { return fechaCreacionHistoria; }
    public void setFechaCreacionHistoria(LocalDateTime fechaCreacionHistoria) { this.fechaCreacionHistoria = fechaCreacionHistoria; }

    public int getConsultaId() { return consultaId; }
    public void setConsultaId(int consultaId) { this.consultaId = consultaId; }

    public LocalDateTime getFechaConsulta() { return fechaConsulta; }
    public void setFechaConsulta(LocalDateTime fechaConsulta) { this.fechaConsulta = fechaConsulta; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }

    public String getNotasConsulta() { return notasConsulta; }
    public void setNotasConsulta(String notasConsulta) { this.notasConsulta = notasConsulta; }

    public String getMedicamentosReceta() { return medicamentosReceta; }
    public void setMedicamentosReceta(String medicamentosReceta) { this.medicamentosReceta = medicamentosReceta; }

    public String getLaboratorios() { return laboratorios; }
    public void setLaboratorios(String laboratorios) { this.laboratorios = laboratorios; }
}
