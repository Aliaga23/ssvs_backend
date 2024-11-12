package com.ssvs.SSVS.backend.model;

public class Permiso {

    private int id;  // Mapeo de la columna permiso_id
    private String nombre;  // Mapeo de la columna nombre
    private String descripcion;  // Mapeo de la columna descripcion

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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