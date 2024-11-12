package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.model.Dia;
import com.ssvs.SSVS.backend.service.DiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dias")
public class DiaController {

    private final DiaService diaService;

    public DiaController(DiaService diaService) {
        this.diaService = diaService;
    }

    @GetMapping
    public List<Dia> getAllDias() {
        return diaService.getAllDias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dia> getDiaById(@PathVariable int id) {
        Dia dia = diaService.getDiaById(id);
        return dia != null ? ResponseEntity.ok(dia) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createDia(@RequestBody Dia dia) {
        int result = diaService.createDia(dia);
        return result > 0 ? ResponseEntity.ok("Dia created successfully") : ResponseEntity.badRequest().body("Error creating Dia");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDia(@PathVariable int id, @RequestBody Dia dia) {
        int result = diaService.updateDia(id, dia);
        return result > 0 ? ResponseEntity.ok("Dia updated successfully") : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDia(@PathVariable int id) {
        int result = diaService.deleteDia(id);
        return result > 0 ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
