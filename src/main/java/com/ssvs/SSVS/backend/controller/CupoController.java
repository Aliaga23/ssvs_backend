package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.dto.CupoInfoDTO;
import com.ssvs.SSVS.backend.model.Cupo;
import com.ssvs.SSVS.backend.service.CupoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cupos")
public class CupoController {
    private final CupoService cupoService;

    public CupoController(CupoService cupoService) {
        this.cupoService = cupoService;
    }

    // Obtener todos los cupos
    @GetMapping
    public ResponseEntity<List<Cupo>> getAllCupos() {
        List<Cupo> cupos = cupoService.getAllCupos();
        return ResponseEntity.ok(cupos);
    }

    // Obtener cupos por medico y especialidad con detalle de estado
    @GetMapping("/medico/{medicoId}/especialidad/{especialidadId}")
    public ResponseEntity<List<CupoInfoDTO>> getCuposByMedicoAndEspecialidad(
            @PathVariable int medicoId,
            @PathVariable int especialidadId
    ) {
        List<CupoInfoDTO> cupos = cupoService.getCuposByMedicoAndEspecialidad(medicoId, especialidadId);
        return ResponseEntity.ok(cupos);
    }

    // Obtener un cupo por id
    @GetMapping("/{id}")
    public ResponseEntity<Cupo> getCupoById(@PathVariable int id) {
        Cupo cupo = cupoService.getCupoById(id);
        return ResponseEntity.ok(cupo);
    }
}
