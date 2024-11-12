// src/main/java/com/ssvs/backend/service/ConsultaMedicaService.java

package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.dto.ConsultaMedicaDTO;
import com.ssvs.SSVS.backend.model.ConsultaMedica;
import com.ssvs.SSVS.backend.repository.ConsultaMedicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaMedicaService {
    private final ConsultaMedicaRepository repository;

    public ConsultaMedicaService(ConsultaMedicaRepository repository) {
        this.repository = repository;
    }

    public List<ConsultaMedicaDTO> getAllConsultasWithDetails() {
        return repository.findAllWithDetails();
    }
    public ConsultaMedicaDTO getConsultaById(int consultaId) {
        return repository.findConsultaById(consultaId);
    }
    public void saveConsulta(ConsultaMedica consulta) {
        repository.save(consulta);
    }

    public void updateConsulta(ConsultaMedica consulta) {
        repository.update(consulta);
    }

    public void deleteConsulta(int consultaId) {
        repository.delete(consultaId);
    }
}
