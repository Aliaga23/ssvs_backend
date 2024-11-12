package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.model.Laboratorio;
import com.ssvs.SSVS.backend.repository.LaboratorioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratorioService {

    private final LaboratorioRepository laboratorioRepository;

    public LaboratorioService(LaboratorioRepository laboratorioRepository) {
        this.laboratorioRepository = laboratorioRepository;
    }

    public List<Laboratorio> getAllLaboratorios() {
        return laboratorioRepository.findAll();
    }

    public Laboratorio getLaboratorioById(int id) {
        return laboratorioRepository.findById(id);
    }

    public void createLaboratorio(Laboratorio laboratorio) {
        laboratorioRepository.save(laboratorio);
    }

    public void updateLaboratorio(int id, Laboratorio laboratorio) {
        laboratorioRepository.update(id, laboratorio);
    }
    public Laboratorio getLaboratorioByConsultaId(int consultaId) {
        return laboratorioRepository.findByConsultaId(consultaId);
    }
    public void deleteLaboratorio(int id) {
        laboratorioRepository.delete(id);
    }
}
