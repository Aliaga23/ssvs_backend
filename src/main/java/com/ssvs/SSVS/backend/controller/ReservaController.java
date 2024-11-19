package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.dto.ReservaInfoDTO;
import com.ssvs.SSVS.backend.service.ReservaService;
import com.ssvs.SSVS.backend.service.SesionService;
import com.ssvs.SSVS.backend.service.IPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    @Autowired
    private SesionService sesionService;

    @Autowired
    private IPService ipService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<ReservaInfoDTO>> getAllReservas() {
        List<ReservaInfoDTO> reservas = reservaService.getAllReservasWithDetails();
        return ResponseEntity.ok(reservas);
    }

    @PostMapping
    public ResponseEntity<Void> createReserva(@RequestBody ReservaInfoDTO reservaInfo) {
        try {
            // Obtener la IP pública del cliente
            String ip = ipService.obtenerIPPublica();

            // Simular el ID del usuario autenticado
            int usuarioId = 1; // Cambiar esto según la lógica de autenticación actual

            // Registrar la sesión en PostgreSQL
            sesionService.establecerSesion(usuarioId, ip);

            // Crear la reserva
            reservaService.saveReserva(
                reservaInfo.getPacienteId(),
                reservaInfo.getCupoId(),
                reservaInfo.getFechaReserva(),
                reservaInfo.getEstado()
            );
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaInfoDTO> getReservaById(@PathVariable int id) {
        ReservaInfoDTO reserva = reservaService.getReservaById(id);
        return reserva != null ? ResponseEntity.ok(reserva) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReserva(
            @PathVariable int id,
            @RequestParam int pacienteId,
            @RequestParam int cupoId,
            @RequestParam LocalDate fechaReserva,
            @RequestParam String estado
    ) {
        reservaService.updateReserva(id, pacienteId, cupoId, fechaReserva, estado);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<ReservaInfoDTO>> getReservasByMedicoId(@PathVariable int medicoId) {
        List<ReservaInfoDTO> reservas = reservaService.getReservasByMedicoId(medicoId);
        return reservas != null && !reservas.isEmpty() ? ResponseEntity.ok(reservas) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ReservaInfoDTO>> getReservasByPacienteId(@PathVariable int pacienteId) {
        List<ReservaInfoDTO> reservas = reservaService.getReservasByPacienteId(pacienteId);
        return reservas != null && !reservas.isEmpty() ? ResponseEntity.ok(reservas) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable int id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }
}
