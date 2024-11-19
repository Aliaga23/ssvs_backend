package com.ssvs.SSVS.backend.model;

public class MetodoDePago {

    private int metodoId; // Mapeo de la columna metodo_id
    private String nombre; // Mapeo de la columna nombre
    private String descripcion; // Mapeo de la columna descripcion

    // Getters y Setters
    public int getMetodoId() {
        return metodoId;
    }

    public void setMetodoId(int metodoId) {
        this.metodoId = metodoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
