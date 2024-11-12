package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.model.Permiso;
import com.ssvs.SSVS.backend.service.PermisoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permisos")
public class PermisoController {

    private final PermisoService permisoService;

    public PermisoController(PermisoService permisoService) {
        this.permisoService = permisoService;
    }

    // Obtener todos los permisos
    @GetMapping
    public List<Permiso> getAllPermisos() {
        return permisoService.getAllPermisos();
    }

    // Obtener un permiso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Permiso> getPermisoById(@PathVariable int id) {
        Permiso permiso = permisoService.getPermisoById(id);
        if (permiso != null) {
            return ResponseEntity.ok(permiso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo permiso
    @PostMapping
    public void createPermiso(@RequestBody Permiso permiso) {
        permisoService.createPermiso(permiso);
    }

    // Actualizar un permiso
    @PutMapping("/{id}")
    public void updatePermiso(@PathVariable int id, @RequestBody Permiso permiso) {
        permisoService.updatePermiso(id, permiso);
    }

    // Eliminar un permiso
    @DeleteMapping("/{id}")
    public void deletePermiso(@PathVariable int id) {
        permisoService.deletePermiso(id);
    }
}