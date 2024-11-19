// src/main/java/com/ssvs/backend/controller/ReservaController.java

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
  
    @GetMapping
    public ResponseEntity<List<ReservaInfoDTO>> getAllReservas() {
        List<ReservaInfoDTO> reservas = reservaService.getAllReservasWithDetails();
        return ResponseEntity.ok(reservas);
    }
@PostMapping
public ResponseEntity<Void> createReserva(@RequestBody ReservaInfoDTO reservaInfo) {

    String ip = ipService.obtenerIPPublica();
    sesionService.establecerSesion(1, ip); // Cambiar '1' por el ID del usuario autenticado.

    // Guardar la reserva

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
