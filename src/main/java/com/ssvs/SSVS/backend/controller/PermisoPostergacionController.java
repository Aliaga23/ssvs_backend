package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.model.PermisoPostergacion;
import com.ssvs.SSVS.backend.service.PermisoPostergacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/permisos-postergacion")
public class PermisoPostergacionController {

    private final PermisoPostergacionService service;

    public PermisoPostergacionController(PermisoPostergacionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PermisoPostergacion>> getAllPermisos() {
        return ResponseEntity.ok(service.getAllPermisos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermisoPostergacion> getPermisoById(@PathVariable int id) {
        PermisoPostergacion permiso = service.getPermisoById(id);
        return permiso != null ? ResponseEntity.ok(permiso) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createPermiso(@RequestBody PermisoPostergacion permiso) {
        service.createPermiso(permiso);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Permiso de postergación creado exitosamente.");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updatePermiso(@PathVariable int id, @RequestBody PermisoPostergacion permiso) {
        permiso.setPostergacionId(id);
        service.updatePermiso(permiso);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Permiso de postergación actualizado exitosamente.");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletePermiso(@PathVariable int id) {
        service.deletePermiso(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Permiso de postergación eliminado exitosamente.");
        return ResponseEntity.ok(response);
    }
}
