package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.dto.PagoSeguroDTO;
import com.ssvs.SSVS.backend.service.PagoSeguroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pagos-seguro")
public class PagoSeguroController {

    private final PagoSeguroService pagoSeguroService;

    public PagoSeguroController(PagoSeguroService pagoSeguroService) {
        this.pagoSeguroService = pagoSeguroService;
    }

    // Registrar un nuevo pago
    @PostMapping("/registrar")
    public ResponseEntity<Void> registrarPagoSeguro(@RequestBody PagoSeguroDTO pagoSeguroDTO) {
        pagoSeguroService.registrarPagoSeguro(
                pagoSeguroDTO.getPacienteId(),
                pagoSeguroDTO.getMonto(),
                pagoSeguroDTO.getFechaVencimiento(),
                pagoSeguroDTO.getMetodoPago()
        );
        return ResponseEntity.ok().build();
    }

    // Obtener el estado del seguro
    @GetMapping("/estado/{pacienteId}")
    public ResponseEntity<Boolean> obtenerEstadoSeguro(@PathVariable int pacienteId) {
        boolean seguroActivo = pagoSeguroService.validarEstadoSeguro(pacienteId);
        return ResponseEntity.ok(seguroActivo);
    }
       @GetMapping("/historial/{pacienteId}")
    public ResponseEntity<List<PagoSeguroDTO>> obtenerHistorialDePagos(@PathVariable int pacienteId) {
        List<PagoSeguroDTO> historial = pagoSeguroService.obtenerHistorialDePagos(pacienteId);
        return ResponseEntity.ok(historial);
    }
}
