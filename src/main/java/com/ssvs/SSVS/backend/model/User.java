// src/main/java/com/ssvs/SSVS/backend/model/User.java
package com.ssvs.SSVS.backend.model;

public class User {
    private int id;             // Mapea el campo usuario_id
    private String nombre;       // Mapea el campo nombre
    private String apellido;     // Mapea el campo apellido
    private String email;        // Mapea el campo email
    private String password;     // Mapea el campo password (renombrado de contraseña)
    private String telefono;     // Mapea el campo telefono
    private String direccion;    // Mapea el campo direccion
    private Integer rolId;       // Mapea el campo rol_id, puede ser nulo si el rol no está asignado

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }
}
