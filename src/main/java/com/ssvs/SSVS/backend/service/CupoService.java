package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.dto.CupoInfoDTO;
import com.ssvs.SSVS.backend.model.Cupo;
import com.ssvs.SSVS.backend.repository.CupoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CupoService {
    private final CupoRepository cupoRepository;

    public CupoService(CupoRepository cupoRepository) {
        this.cupoRepository = cupoRepository;
    }

    public List<CupoInfoDTO> getCuposByMedicoAndEspecialidad(int medicoId, int especialidadId) {
        return cupoRepository.findCuposByMedicoAndEspecialidad(medicoId, especialidadId);
    }

    public List<Cupo> getAllCupos() {
        return cupoRepository.findAll();
    }

    public Cupo getCupoById(int id) {
        return cupoRepository.findById(id);
    }
}
