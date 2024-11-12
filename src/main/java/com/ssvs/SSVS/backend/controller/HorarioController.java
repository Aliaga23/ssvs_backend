// src/main/java/com/ssvs/SSVS/backend/controller/HorarioController.java
package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.model.Horario;
import com.ssvs.SSVS.backend.service.HorarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
public class HorarioController {

    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @GetMapping
    public List<Horario> getAllHorarios() {
        return horarioService.getAllHorarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horario> getHorarioById(@PathVariable int id) {
        Horario horario = horarioService.getHorarioById(id);
        return horario != null ? ResponseEntity.ok(horario) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Horario> createHorario(@RequestBody Horario horario) {
        horarioService.createHorario(horario);
        return ResponseEntity.ok(horario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Horario> updateHorario(@PathVariable int id, @RequestBody Horario horario) {
        Horario existingHorario = horarioService.getHorarioById(id);
        if (existingHorario != null) {
            horarioService.updateHorario(id, horario);
            return ResponseEntity.ok(horario);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorario(@PathVariable int id) {
        Horario existingHorario = horarioService.getHorarioById(id);
        if (existingHorario != null) {
            horarioService.deleteHorario(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
