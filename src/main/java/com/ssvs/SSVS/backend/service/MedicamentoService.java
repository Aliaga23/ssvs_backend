package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.model.Medicamento;
import com.ssvs.SSVS.backend.repository.MedicamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoService(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    public List<Medicamento> getAllMedicamentos() {
        return medicamentoRepository.findAll();
    }

    public Medicamento getMedicamentoById(int id) {
        return medicamentoRepository.findById(id);
    }

    public void createMedicamento(Medicamento medicamento) {
        medicamentoRepository.save(medicamento);
    }

    public void updateMedicamento(int id, Medicamento medicamento) {
        medicamentoRepository.update(id, medicamento);
    }

    public void deleteMedicamento(int id) {
        medicamentoRepository.delete(id);
    }
}
