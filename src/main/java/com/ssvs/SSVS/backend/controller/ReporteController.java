package com.ssvs.SSVS.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssvs.SSVS.backend.dto.PacienteConsultaDTO;
import com.ssvs.SSVS.backend.service.ReporteService;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService pacienteService;

    /**
     * Endpoint para obtener un reporte completo de las consultas de los pacientes.
     * 
     * @return Lista de reportes con la información del paciente, historia clínica, consulta, medicamentos, etc.
     */
    @GetMapping("/consultas")
    public ResponseEntity<List<PacienteConsultaDTO>> obtenerReporteConsultas() {
        List<PacienteConsultaDTO> consultas = pacienteService.obtenerReportePacientes();
        return ResponseEntity.ok(consultas);
    }
}
