package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.model.HistoriaClinica;
import com.ssvs.SSVS.backend.service.HistoriaClinicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ssvs.SSVS.backend.model.HistoriaClinicaCompletaDTO;

import java.util.List;

@RestController
@RequestMapping("/api/historias-clinicas")
public class HistoriaClinicaController {

    private final HistoriaClinicaService historiaClinicaService;

    public HistoriaClinicaController(HistoriaClinicaService historiaClinicaService) {
        this.historiaClinicaService = historiaClinicaService;
    }

    @GetMapping
    public List<HistoriaClinica> getAllHistoriasClinicas() {
        return historiaClinicaService.getAllHistoriasClinicas();
    }
    @GetMapping("/resumen/{pacienteId}")
    public List<HistoriaClinicaCompletaDTO> getHistoriaClinicaResumen(@PathVariable int pacienteId) {
        return historiaClinicaService.getHistoriaClinicaResumenByPacienteId(pacienteId);
    }
    @GetMapping("/{id}")
    public ResponseEntity<HistoriaClinica> getHistoriaClinicaById(@PathVariable int id) {
        HistoriaClinica historiaClinica = historiaClinicaService.getHistoriaClinicaById(id);
        if (historiaClinica != null) {
            return ResponseEntity.ok(historiaClinica);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<HistoriaClinica> createHistoriaClinica(@RequestBody HistoriaClinica historiaClinica) {
        historiaClinicaService.createHistoriaClinica(historiaClinica);
        return ResponseEntity.ok(historiaClinica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoriaClinica> updateHistoriaClinica(@PathVariable int id, @RequestBody HistoriaClinica historiaClinica) {
        HistoriaClinica existingHistoria = historiaClinicaService.getHistoriaClinicaById(id);
        if (existingHistoria != null) {
            historiaClinicaService.updateHistoriaClinica(id, historiaClinica);
            return ResponseEntity.ok(historiaClinica);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistoriaClinica(@PathVariable int id) {
        HistoriaClinica existingHistoria = historiaClinicaService.getHistoriaClinicaById(id);
        if (existingHistoria != null) {
            historiaClinicaService.deleteHistoriaClinica(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
