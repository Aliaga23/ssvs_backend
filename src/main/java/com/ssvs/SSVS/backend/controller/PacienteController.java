package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.model.Paciente;
import com.ssvs.SSVS.backend.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteService.getAllPacientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable int id) {
        Paciente paciente = pacienteService.getPacienteById(id);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void createPaciente(@RequestBody Paciente paciente) {
        pacienteService.createPaciente(paciente);
    }

    @PutMapping("/{id}")
    public void updatePaciente(@PathVariable int id, @RequestBody Paciente paciente) {
        pacienteService.updatePaciente(id, paciente);
    }

    @DeleteMapping("/{id}")
    public void deletePaciente(@PathVariable int id) {
        pacienteService.deletePaciente(id);
    }
     // Endpoint para actualizar el estado del seguro del paciente
    @PutMapping("/{pacienteId}/estado-seguro")
    public ResponseEntity<Void> actualizarEstadoSeguro(
            @PathVariable int pacienteId,
            @RequestParam boolean seguroActivo,
            @RequestParam(required = false) LocalDate fechaVencimiento) {
        pacienteService.actualizarEstadoSeguro(pacienteId, seguroActivo, fechaVencimiento);
        return ResponseEntity.ok().build();
    }
}
