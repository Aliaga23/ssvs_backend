package com.ssvs.SSVS.backend.dto;

import java.time.LocalDateTime;

public class RecetaDetallesDTO {
    private LocalDateTime fechaEmision;
    private String instrucciones;
    private LocalDateTime fechaConsulta;
    private String diagnostico;
    private String tratamiento;
    private String notas;
    private String pacienteNombre;
    private String pacienteApellido;
    private String medicoNombre;
    private String medicoApellido;
    private String especialidadNombre;
    private String diaAtencion;
    private String consultaHoraInicio;
    private String consultaHoraFinal;
    private String medicamentoNombre;
    private String medicamentoDosis;
    private String medicamentoFrecuencia;
    private int medicamentoDuracion;

    // Getters y Setters
    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public LocalDateTime getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(LocalDateTime fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public void setPacienteNombre(String pacienteNombre) {
        this.pacienteNombre = pacienteNombre;
    }

    public String getPacienteApellido() {
        return pacienteApellido;
    }

    public void setPacienteApellido(String pacienteApellido) {
        this.pacienteApellido = pacienteApellido;
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

    public String getEspecialidadNombre() {
        return especialidadNombre;
    }

    public void setEspecialidadNombre(String especialidadNombre) {
        this.especialidadNombre = especialidadNombre;
    }

    public String getDiaAtencion() {
        return diaAtencion;
    }

    public void setDiaAtencion(String diaAtencion) {
        this.diaAtencion = diaAtencion;
    }

    public String getConsultaHoraInicio() {
        return consultaHoraInicio;
    }

    public void setConsultaHoraInicio(String consultaHoraInicio) {
        this.consultaHoraInicio = consultaHoraInicio;
    }

    public String getConsultaHoraFinal() {
        return consultaHoraFinal;
    }

    public void setConsultaHoraFinal(String consultaHoraFinal) {
        this.consultaHoraFinal = consultaHoraFinal;
    }

    public String getMedicamentoNombre() {
        return medicamentoNombre;
    }

    public void setMedicamentoNombre(String medicamentoNombre) {
        this.medicamentoNombre = medicamentoNombre;
    }

    public String getMedicamentoDosis() {
        return medicamentoDosis;
    }

    public void setMedicamentoDosis(String medicamentoDosis) {
        this.medicamentoDosis = medicamentoDosis;
    }

    public String getMedicamentoFrecuencia() {
        return medicamentoFrecuencia;
    }

    public void setMedicamentoFrecuencia(String medicamentoFrecuencia) {
        this.medicamentoFrecuencia = medicamentoFrecuencia;
    }

    public int getMedicamentoDuracion() {
        return medicamentoDuracion;
    }

    public void setMedicamentoDuracion(int medicamentoDuracion) {
        this.medicamentoDuracion = medicamentoDuracion;
    }
}
