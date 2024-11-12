// src/main/java/com/ssvs/SSVS/backend/controller/MedicoEspecialidadController.java
package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.model.MedicoEspecialidad;
import com.ssvs.SSVS.backend.service.MedicoEspecialidadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medico-especialidad")
public class MedicoEspecialidadController {
    private final MedicoEspecialidadService service;

    public MedicoEspecialidadController(MedicoEspecialidadService service) {
        this.service = service;
    }

    @GetMapping("/details")
    public List<MedicoEspecialidad> getAllWithDetails() {
        return service.findAllWithDetails();
    }

    @GetMapping("/{medicoId}/especialidades")
    public List<MedicoEspecialidad> getEspecialidadesByMedicoId(@PathVariable int medicoId) {
        return service.findEspecialidadesByMedicoId(medicoId);
    }

    @PostMapping("/{medicoId}/especialidades/{especialidadId}")
    public ResponseEntity<Void> addEspecialidadToMedico(@PathVariable int medicoId, @PathVariable int especialidadId) {
        service.addEspecialidadToMedico(medicoId, especialidadId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{medicoId}/especialidades/{especialidadId}")
    public ResponseEntity<Void> removeEspecialidadFromMedico(@PathVariable int medicoId, @PathVariable int especialidadId) {
        service.removeEspecialidadFromMedico(medicoId, especialidadId);
        return ResponseEntity.noContent().build();
    }
}
