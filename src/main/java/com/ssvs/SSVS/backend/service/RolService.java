package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.model.Rol;
import com.ssvs.SSVS.backend.repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    // Obtener todos los roles
    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }

    // Obtener un rol por ID
    public Rol getRolById(int id) {
        return rolRepository.findById(id);
    }

    // Crear un nuevo rol
    public void createRol(Rol rol) {
        rolRepository.save(rol);
    }

    // Actualizar un rol
    public void updateRol(int id, Rol rol) {
        rolRepository.update(id, rol);
    }

    // Eliminar un rol
    public void deleteRol(int id) {
        rolRepository.delete(id);
    }
}
