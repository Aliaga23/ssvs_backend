package com.ssvs.SSVS.backend.dto;

import java.time.LocalDate;

public class PagoSeguroDTO {
    private int pagoId;
    private int pacienteId;
    private double monto;
    private LocalDate fechaPago;
    private LocalDate fechaVencimiento;
    private int metodoId; // Nuevo campo para metodoId
    private String metodoPago; // Mantener metodoPago como nombre descriptivo
    private String estado;

    // Getters y Setters
    public int getPagoId() {
        return pagoId;
    }

    public void setPagoId(int pagoId) {
        this.pagoId = pagoId;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getMetodoId() {
        return metodoId;
    }

    public void setMetodoId(int metodoId) {
        this.metodoId = metodoId;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
