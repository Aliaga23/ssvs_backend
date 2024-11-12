package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.model.RolPermisos;
import com.ssvs.SSVS.backend.service.RolPermisosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rol-permisos")
public class RolPermisosController {

    private final RolPermisosService rolPermisosService;

    public RolPermisosController(RolPermisosService rolPermisosService) {
        this.rolPermisosService = rolPermisosService;
    }

    // Obtener todos los permisos de todos los roles
    @GetMapping
    public List<RolPermisos> getAllRolPermisos() {
        return rolPermisosService.getAllRolPermisos();
    }

    // Obtener permisos de un rol específico
    @GetMapping("/rol/{rolId}")
    public List<RolPermisos> getPermisosByRolId(@PathVariable int rolId) {
        return rolPermisosService.getPermisosByRolId(rolId);
    }

    // Añadir permiso a un rol
    @PostMapping
    public ResponseEntity<RolPermisos> addPermisoToRol(@RequestBody RolPermisos rolPermisos) {
        rolPermisosService.addPermisoToRol(rolPermisos);
        return ResponseEntity.ok(rolPermisos);
    }

    // Eliminar un permiso de un rol
    @DeleteMapping
    public ResponseEntity<Void> removePermisoFromRol(@RequestParam int rolId, @RequestParam int permisoId) {
        rolPermisosService.removePermisoFromRol(rolId, permisoId);
        return ResponseEntity.noContent().build();
    }
}
