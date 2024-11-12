// src/main/java/com/ssvs/SSVS/backend/controller/HorarioMedicoEspecialidadController.java
package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.model.HorarioMedicoEspecialidad;
import com.ssvs.SSVS.backend.service.HorarioMedicoEspecialidadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horario-medico-especialidad")
public class HorarioMedicoEspecialidadController {
    private final HorarioMedicoEspecialidadService service;

    public HorarioMedicoEspecialidadController(HorarioMedicoEspecialidadService service) {
        this.service = service;
    }

    @GetMapping
    public List<HorarioMedicoEspecialidad> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioMedicoEspecialidad> getById(@PathVariable int id) {
        HorarioMedicoEspecialidad hme = service.findById(id);
        if (hme != null) {
            return ResponseEntity.ok(hme);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody HorarioMedicoEspecialidad hme) {
        service.save(hme);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody HorarioMedicoEspecialidad hme) {
        if (service.findById(id) != null) {
            service.update(id, hme);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
// src/main/java/com/ssvs/SSVS/backend/controller/HorarioMedicoEspecialidadController.java

@GetMapping("/especialidad/{especialidadId}/medicos")
public List<HorarioMedicoEspecialidad> getMedicosByEspecialidad(@PathVariable int especialidadId) {
    return service.findMedicosByEspecialidadId(especialidadId);
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (service.findById(id) != null) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
