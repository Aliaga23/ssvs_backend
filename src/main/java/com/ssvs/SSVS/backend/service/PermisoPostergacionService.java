package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.model.PermisoPostergacion;
import com.ssvs.SSVS.backend.repository.PermisoPostergacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermisoPostergacionService {

    private final PermisoPostergacionRepository repository;

    public PermisoPostergacionService(PermisoPostergacionRepository repository) {
        this.repository = repository;
    }

    public List<PermisoPostergacion> getAllPermisos() {
        return repository.findAll();
    }

    public PermisoPostergacion getPermisoById(int id) {
        return repository.findById(id);
    }

    public void createPermiso(PermisoPostergacion permiso) {
        repository.save(permiso);
    }

    public void updatePermiso(PermisoPostergacion permiso) {
        repository.update(permiso);
    }

    public void deletePermiso(int id) {
        repository.delete(id);
    }
}
