package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.dto.EvaluacionDTO;
import com.ssvs.SSVS.backend.service.EvaluacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    public EvaluacionController(EvaluacionService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }

    @PostMapping
    public ResponseEntity<Void> guardarEvaluacion(@RequestBody EvaluacionDTO evaluacion) {
        evaluacionService.guardarEvaluacion(evaluacion);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{medicoId}/{especialidadId}")
    public ResponseEntity<List<EvaluacionDTO>> obtenerEvaluacionesPorMedicoYEspecialidad(
            @PathVariable int medicoId, @PathVariable int especialidadId) {
        List<EvaluacionDTO> evaluaciones = evaluacionService.obtenerEvaluacionesPorMedicoYEspecialidad(medicoId, especialidadId);
        return ResponseEntity.ok(evaluaciones);
    }

    @GetMapping("/promedio/{medicoId}/{especialidadId}")
    public ResponseEntity<Double> obtenerPromedioEvaluacion(
            @PathVariable int medicoId, @PathVariable int especialidadId) {
        Double promedio = evaluacionService.obtenerPromedioEvaluacion(medicoId, especialidadId);
        return ResponseEntity.ok(promedio);
    }

    // Nuevo endpoint para obtener todas las evaluaciones
    @GetMapping
    public ResponseEntity<List<EvaluacionDTO>> obtenerTodasLasEvaluaciones() {
        List<EvaluacionDTO> evaluaciones = evaluacionService.obtenerTodasLasEvaluaciones();
        return ResponseEntity.ok(evaluaciones);
    }
}