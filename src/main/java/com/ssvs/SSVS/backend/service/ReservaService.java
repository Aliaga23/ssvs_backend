// src/main/java/com/ssvs/backend/service/ReservaService.java

package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.dto.ReservaInfoDTO;
import com.ssvs.SSVS.backend.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }
  
    
    public List<ReservaInfoDTO> getAllReservasWithDetails() {
        return reservaRepository.findReservasWithDetails();
    }
    public ReservaInfoDTO getReservaById(int reservaId) {
        return reservaRepository.findReservaById(reservaId);
    }

    public void saveReserva(int pacienteId, int cupoId, LocalDate fechaReserva, String estado) {
        reservaRepository.saveReserva(pacienteId, cupoId, fechaReserva, estado);
    }

    public void updateReserva(int reservaId, int pacienteId, int cupoId, LocalDate fechaReserva, String estado) {
        reservaRepository.updateReserva(reservaId, pacienteId, cupoId, fechaReserva, estado);
    }
    // src/main/java/com/ssvs/SSVS/backend/service/ReservaService.java
public List<ReservaInfoDTO> getReservasByMedicoId(int medicoId) {
    return reservaRepository.findReservasByMedicoId(medicoId);
}

    public void deleteReserva(int reservaId) {
        reservaRepository.deleteReserva(reservaId);
    }
}
