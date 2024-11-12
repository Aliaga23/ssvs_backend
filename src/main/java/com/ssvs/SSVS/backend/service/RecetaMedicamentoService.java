package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.model.RecetaMedicamento;
import com.ssvs.SSVS.backend.repository.RecetaMedicamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetaMedicamentoService {

    private final RecetaMedicamentoRepository recetaMedicamentoRepository;

    public RecetaMedicamentoService(RecetaMedicamentoRepository recetaMedicamentoRepository) {
        this.recetaMedicamentoRepository = recetaMedicamentoRepository;
    }

    public List<RecetaMedicamento> getAllRecetasMedicamentos() {
        return recetaMedicamentoRepository.findAll();
    }

    public List<RecetaMedicamento> getMedicamentosByRecetaId(int recetaId) {
        return recetaMedicamentoRepository.findByRecetaId(recetaId);
    }

    public void addMedicamentoToReceta(RecetaMedicamento recetaMedicamento) {
        recetaMedicamentoRepository.save(recetaMedicamento);
    }

    public void removeMedicamentoFromReceta(int recetaId, int medicamentoId) {
        recetaMedicamentoRepository.delete(recetaId, medicamentoId);
    }
}
