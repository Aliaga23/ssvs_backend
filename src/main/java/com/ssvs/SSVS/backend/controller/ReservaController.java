package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.dto.ReservaInfoDTO;
import com.ssvs.SSVS.backend.service.ReservaService;
import com.ssvs.SSVS.backend.service.IPService;
import com.ssvs.SSVS.backend.service.SesionService;
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
    private final SesionService sesionService;
    private final IPService ipService;

    @Autowired
    public ReservaController(ReservaService reservaService, SesionService sesionService, IPService ipService) {
        this.reservaService = reservaService;
        this.sesionService = sesionService;
        this.ipService = ipService;
    }

    // Obtener todas las reservas
    @GetMapping
    public ResponseEntity<List<ReservaInfoDTO>> getAllReservas() {
        List<ReservaInfoDTO> reservas = reservaService.getAllReservasWithDetails();
        return ResponseEntity.ok(reservas);
    }

    // Crear una nueva reserva
    @PostMapping
    public ResponseEntity<String> createReserva(@RequestBody ReservaInfoDTO reservaInfo) {
        try {
            // Registrar sesión
            String ip = ipService.obtenerIPPublica();
            sesionService.establecerSesion(1, ip); // Cambiar '1' por el ID del usuario autenticado.

            // Guardar la reserva
            reservaService.saveReserva(
                reservaInfo.getPacienteId(),
                reservaInfo.getCupoId(),
                reservaInfo.getFechaReserva(),
                reservaInfo.getEstado()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body("Reserva creada exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al crear la reserva: " + e.getMessage());
        }
    }

    // Obtener una reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservaInfoDTO> getReservaById(@PathVariable int id) {
        ReservaInfoDTO reserva = reservaService.getReservaById(id);
        return reserva != null ? ResponseEntity.ok(reserva) : ResponseEntity.notFound().build();
    }

    // Actualizar una reserva
    @PutMapping("/{id}")
    public ResponseEntity<String> updateReserva(
            @PathVariable int id,
            @RequestParam int pacienteId,
            @RequestParam int cupoId,
            @RequestParam LocalDate fechaReserva,
            @RequestParam String estado
    ) {
        try {
            reservaService.updateReserva(id, pacienteId, cupoId, fechaReserva, estado);
            return ResponseEntity.ok("Reserva actualizada exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al actualizar la reserva: " + e.getMessage());
        }
    }

    // Obtener reservas por ID de médico
    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<ReservaInfoDTO>> getReservasByMedicoId(@PathVariable int medicoId) {
        List<ReservaInfoDTO> reservas = reservaService.getReservasByMedicoId(medicoId);
        return reservas != null && !reservas.isEmpty()
            ? ResponseEntity.ok(reservas)
            : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Obtener reservas por ID de paciente
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ReservaInfoDTO>> getReservasByPacienteId(@PathVariable int pacienteId) {
        List<ReservaInfoDTO> reservas = reservaService.getReservasByPacienteId(pacienteId);
        return reservas != null && !reservas.isEmpty()
            ? ResponseEntity.ok(reservas)
            : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Eliminar una reserva por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReserva(@PathVariable int id) {
        try {
            reservaService.deleteReserva(id);
            return ResponseEntity.ok("Reserva eliminada exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al eliminar la reserva: " + e.getMessage());
        }
    }
}
