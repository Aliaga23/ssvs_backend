package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.model.HistoriaClinica;
import com.ssvs.SSVS.backend.repository.HistoriaClinicaRepository;
import org.springframework.stereotype.Service;
import com.ssvs.SSVS.backend.model.HistoriaClinicaCompletaDTO;

import java.util.List;

@Service
public class HistoriaClinicaService {

    private final HistoriaClinicaRepository historiaClinicaRepository;

    public HistoriaClinicaService(HistoriaClinicaRepository historiaClinicaRepository) {
        this.historiaClinicaRepository = historiaClinicaRepository;
    }

    public List<HistoriaClinica> getAllHistoriasClinicas() {
        return historiaClinicaRepository.findAll();
    }

    public HistoriaClinica getHistoriaClinicaById(int id) {
        return historiaClinicaRepository.findById(id);
    }

    public void createHistoriaClinica(HistoriaClinica historiaClinica) {
        historiaClinicaRepository.save(historiaClinica);
    }

    public void updateHistoriaClinica(int id, HistoriaClinica historiaClinica) {
        historiaClinicaRepository.update(id, historiaClinica);
    }
    public List<HistoriaClinicaCompletaDTO> getHistoriaClinicaResumenByPacienteId(int pacienteId) {
        return historiaClinicaRepository.findHistoriaClinicaResumenByPacienteId(pacienteId);
    }
    public void deleteHistoriaClinica(int id) {
        historiaClinicaRepository.delete(id);
    }
}
