package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.model.Jornada;
import com.ssvs.SSVS.backend.service.JornadaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jornadas")
public class JornadaController {

    private final JornadaService jornadaService;

    public JornadaController(JornadaService jornadaService) {
        this.jornadaService = jornadaService;
    }

    @GetMapping
    public List<Jornada> getAllJornadas() {
        return jornadaService.getAllJornadas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jornada> getJornadaById(@PathVariable int id) {
        Jornada jornada = jornadaService.getJornadaById(id);
        return jornada != null ? ResponseEntity.ok(jornada) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createJornada(@RequestBody Jornada jornada) {
        int result = jornadaService.createJornada(jornada);
        return result > 0 ? ResponseEntity.ok("Jornada created successfully") : ResponseEntity.badRequest().body("Error creating Jornada");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJornada(@PathVariable int id, @RequestBody Jornada jornada) {
        int result = jornadaService.updateJornada(id, jornada);
        return result > 0 ? ResponseEntity.ok("Jornada updated successfully") : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJornada(@PathVariable int id) {
        int result = jornadaService.deleteJornada(id);
        return result > 0 ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
