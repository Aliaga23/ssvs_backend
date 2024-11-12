package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.model.RolPermisos;
import com.ssvs.SSVS.backend.repository.RolPermisosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolPermisosService {

    private final RolPermisosRepository rolPermisosRepository;

    public RolPermisosService(RolPermisosRepository rolPermisosRepository) {
        this.rolPermisosRepository = rolPermisosRepository;
    }

    public List<RolPermisos> getAllRolPermisos() {
        return rolPermisosRepository.findAll();
    }

    public List<RolPermisos> getPermisosByRolId(int rolId) {
        return rolPermisosRepository.findByRolId(rolId);
    }

    public void addPermisoToRol(RolPermisos rolPermisos) {
        rolPermisosRepository.save(rolPermisos);
    }

    public void removePermisoFromRol(int rolId, int permisoId) {
        rolPermisosRepository.delete(rolId, permisoId);
    }
}
