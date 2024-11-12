package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.model.Rol;
import com.ssvs.SSVS.backend.service.RolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    // Obtener todos los roles
    @GetMapping
    public List<Rol> getAllRoles() {
        return rolService.getAllRoles();
    }

    // Obtener un rol por ID
    @GetMapping("/{id}")
    public ResponseEntity<Rol> getRolById(@PathVariable int id) {
        Rol rol = rolService.getRolById(id);
        if (rol != null) {
            return ResponseEntity.ok(rol);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo rol
    @PostMapping
    public void createRol(@RequestBody Rol rol) {
        rolService.createRol(rol);
    }

    // Actualizar un rol
    @PutMapping("/{id}")
    public void updateRol(@PathVariable int id, @RequestBody Rol rol) {
        rolService.updateRol(id, rol);
    }

    // Eliminar un rol
    @DeleteMapping("/{id}")
    public void deleteRol(@PathVariable int id) {
        rolService.deleteRol(id);
    }
}