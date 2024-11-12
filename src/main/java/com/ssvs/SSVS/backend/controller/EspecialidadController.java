package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.model.Especialidad;
import com.ssvs.SSVS.backend.service.EspecialidadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    public EspecialidadController(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @GetMapping
    public List<Especialidad> getAllEspecialidades() {
        return especialidadService.getAllEspecialidades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> getEspecialidadById(@PathVariable int id) {
        Especialidad especialidad = especialidadService.getEspecialidadById(id);
        if (especialidad != null) {
            return ResponseEntity.ok(especialidad);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void createEspecialidad(@RequestBody Especialidad especialidad) {
        especialidadService.createEspecialidad(especialidad);
    }

    @PutMapping("/{id}")
    public void updateEspecialidad(@PathVariable int id, @RequestBody Especialidad especialidad) {
        especialidadService.updateEspecialidad(id, especialidad);
    }

    @DeleteMapping("/{id}")
    public void deleteEspecialidad(@PathVariable int id) {
        especialidadService.deleteEspecialidad(id);
    }
}
