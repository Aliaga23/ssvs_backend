package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.model.Permiso;
import com.ssvs.SSVS.backend.repository.PermisoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermisoService {

    private final PermisoRepository permisoRepository;

    public PermisoService(PermisoRepository permisoRepository) {
        this.permisoRepository = permisoRepository;
    }

    // Obtener todos los permisos
    public List<Permiso> getAllPermisos() {
        return permisoRepository.findAll();
    }

    // Obtener un permiso por ID
    public Permiso getPermisoById(int id) {
        return permisoRepository.findById(id);
    }

    // Crear un nuevo permiso
    public void createPermiso(Permiso permiso) {
        permisoRepository.save(permiso);
    }

    // Actualizar un permiso
    public void updatePermiso(int id, Permiso permiso) {
        permisoRepository.update(id, permiso);
    }

    // Eliminar un permiso
    public void deletePermiso(int id) {
        permisoRepository.delete(id);
    }
}