package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.model.Medico;
import com.ssvs.SSVS.backend.service.MedicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    public List<Medico> getAllMedicos() {
        return medicoService.getAllMedicos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> getMedicoById(@PathVariable int id) {
        Medico medico = medicoService.getMedicoById(id);
        if (medico != null) {
            return ResponseEntity.ok(medico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void createMedico(@RequestBody Medico medico) {
        medicoService.createMedico(medico);
    }

    @PutMapping("/{id}")
    public void updateMedico(@PathVariable int id, @RequestBody Medico medico) {
        medicoService.updateMedico(id, medico);
    }

    @DeleteMapping("/{id}")
    public void deleteMedico(@PathVariable int id) {
        medicoService.deleteMedico(id);
    }
}
