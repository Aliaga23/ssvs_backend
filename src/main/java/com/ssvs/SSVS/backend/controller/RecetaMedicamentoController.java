package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.model.RecetaMedicamento;
import com.ssvs.SSVS.backend.service.RecetaMedicamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recetas-medicamentos")
public class RecetaMedicamentoController {

    private final RecetaMedicamentoService recetaMedicamentoService;

    public RecetaMedicamentoController(RecetaMedicamentoService recetaMedicamentoService) {
        this.recetaMedicamentoService = recetaMedicamentoService;
    }

    @GetMapping
    public List<RecetaMedicamento> getAllRecetasMedicamentos() {
        return recetaMedicamentoService.getAllRecetasMedicamentos();
    }

    @GetMapping("/receta/{recetaId}")
    public List<RecetaMedicamento> getMedicamentosByRecetaId(@PathVariable int recetaId) {
        return recetaMedicamentoService.getMedicamentosByRecetaId(recetaId);
    }

    @PostMapping
    public ResponseEntity<RecetaMedicamento> addMedicamentoToReceta(@RequestBody RecetaMedicamento recetaMedicamento) {
        recetaMedicamentoService.addMedicamentoToReceta(recetaMedicamento);
        return ResponseEntity.ok(recetaMedicamento);
    }

    @DeleteMapping
    public ResponseEntity<Void> removeMedicamentoFromReceta(@RequestParam int recetaId, @RequestParam int medicamentoId) {
        recetaMedicamentoService.removeMedicamentoFromReceta(recetaId, medicamentoId);
        return ResponseEntity.noContent().build();
    }
}
