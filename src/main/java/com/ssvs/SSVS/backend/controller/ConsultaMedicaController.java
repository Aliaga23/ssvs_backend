// src/main/java/com/ssvs/backend/controller/ConsultaMedicaController.java

package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.dto.ConsultaMedicaDTO;
import com.ssvs.SSVS.backend.model.ConsultaMedica;
import com.ssvs.SSVS.backend.service.ConsultaMedicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaMedicaController {

    @Autowired
    private ConsultaMedicaService consultaMedicaService;

    @GetMapping
    public ResponseEntity<List<ConsultaMedicaDTO>> getAllConsultas() {
        List<ConsultaMedicaDTO> consultas = consultaMedicaService.getAllConsultasWithDetails();
        return ResponseEntity.ok(consultas);
    }
  // Obtener una consulta médica específica por ID
    @GetMapping("/{id}")
    public ResponseEntity<ConsultaMedicaDTO> getConsultaById(@PathVariable int id) {
        ConsultaMedicaDTO consulta = consultaMedicaService.getConsultaById(id);
        return consulta != null ? ResponseEntity.ok(consulta) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
 @PostMapping
public ResponseEntity<Map<String, String>> createConsulta(@RequestBody ConsultaMedica consulta) {
    consultaMedicaService.saveConsulta(consulta);
    Map<String, String> response = new HashMap<>();
    response.put("message", "Consulta creada exitosamente");
    return ResponseEntity.ok(response);
}

    @PutMapping("/{id}")
    public ResponseEntity<String> updateConsulta(@PathVariable int id, @RequestBody ConsultaMedica consulta) {
        consulta.setConsultaId(id);
        consultaMedicaService.updateConsulta(consulta);
        return ResponseEntity.ok("Consulta actualizada exitosamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteConsulta(@PathVariable int id) {
        consultaMedicaService.deleteConsulta(id);
        return ResponseEntity.ok("Consulta eliminada exitosamente");
    }
}
