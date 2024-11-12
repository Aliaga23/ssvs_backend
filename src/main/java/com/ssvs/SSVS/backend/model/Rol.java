package com.ssvs.SSVS.backend.model;

public class Rol {

    private int id;  // Mapeo de la columna rol_id
    private String nombre;  // Mapeo de la columna nombre

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
}
