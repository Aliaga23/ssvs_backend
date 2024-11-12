package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.model.Laboratorio;
import com.ssvs.SSVS.backend.service.LaboratorioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laboratorio")
public class LaboratorioController {

    private final LaboratorioService laboratorioService;

    public LaboratorioController(LaboratorioService laboratorioService) {
        this.laboratorioService = laboratorioService;
    }
    // Endpoint para obtener laboratorio por consultaId
    @GetMapping("/consulta/{consultaId}")
    public ResponseEntity<Laboratorio> getLaboratorioByConsultaId(@PathVariable int consultaId) {
        try {
            Laboratorio laboratorio = laboratorioService.getLaboratorioByConsultaId(consultaId);
            return ResponseEntity.ok(laboratorio);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public List<Laboratorio> getAllLaboratorios() {
        return laboratorioService.getAllLaboratorios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laboratorio> getLaboratorioById(@PathVariable int id) {
        Laboratorio laboratorio = laboratorioService.getLaboratorioById(id);
        if (laboratorio != null) {
            return ResponseEntity.ok(laboratorio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Laboratorio> createLaboratorio(@RequestBody Laboratorio laboratorio) {
        laboratorioService.createLaboratorio(laboratorio);
        return ResponseEntity.ok(laboratorio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Laboratorio> updateLaboratorio(@PathVariable int id, @RequestBody Laboratorio laboratorio) {
        Laboratorio existingLaboratorio = laboratorioService.getLaboratorioById(id);
        if (existingLaboratorio != null) {
            laboratorioService.updateLaboratorio(id, laboratorio);
            return ResponseEntity.ok(laboratorio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaboratorio(@PathVariable int id) {
        Laboratorio existingLaboratorio = laboratorioService.getLaboratorioById(id);
        if (existingLaboratorio != null) {
            laboratorioService.deleteLaboratorio(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
