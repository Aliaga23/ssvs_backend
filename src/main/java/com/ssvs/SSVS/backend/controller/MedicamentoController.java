package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.model.Medicamento;
import com.ssvs.SSVS.backend.service.MedicamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @GetMapping
    public List<Medicamento> getAllMedicamentos() {
        return medicamentoService.getAllMedicamentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> getMedicamentoById(@PathVariable int id) {
        Medicamento medicamento = medicamentoService.getMedicamentoById(id);
        if (medicamento != null) {
            return ResponseEntity.ok(medicamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Medicamento> createMedicamento(@RequestBody Medicamento medicamento) {
        medicamentoService.createMedicamento(medicamento);
        return ResponseEntity.ok(medicamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> updateMedicamento(@PathVariable int id, @RequestBody Medicamento medicamento) {
        Medicamento existingMedicamento = medicamentoService.getMedicamentoById(id);
        if (existingMedicamento != null) {
            medicamentoService.updateMedicamento(id, medicamento);
            return ResponseEntity.ok(medicamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicamento(@PathVariable int id) {
        Medicamento existingMedicamento = medicamentoService.getMedicamentoById(id);
        if (existingMedicamento != null) {
            medicamentoService.deleteMedicamento(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
