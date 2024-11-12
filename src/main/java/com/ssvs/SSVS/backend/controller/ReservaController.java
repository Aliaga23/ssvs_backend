// src/main/java/com/ssvs/backend/controller/ReservaController.java

package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.dto.ReservaInfoDTO;
import com.ssvs.SSVS.backend.service.ReservaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    private final ReservaService reservaService;

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
    reservaService.saveReserva(
        reservaInfo.getPacienteId(),
        reservaInfo.getCupoId(),
        reservaInfo.getFechaReserva(),
        reservaInfo.getEstado()
    );
    return ResponseEntity.ok().build();
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
// src/main/java/com/ssvs/SSVS/backend/controller/ReservaController.java
@GetMapping("/medico/{medicoId}")
public ResponseEntity<List<ReservaInfoDTO>> getReservasByMedicoId(@PathVariable int medicoId) {
    List<ReservaInfoDTO> reservas = reservaService.getReservasByMedicoId(medicoId);
    return reservas != null && !reservas.isEmpty() ? ResponseEntity.ok(reservas) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable int id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }
}
