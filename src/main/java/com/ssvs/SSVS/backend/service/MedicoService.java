package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.model.Medico;
import com.ssvs.SSVS.backend.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public List<Medico> getAllMedicos() {
        return medicoRepository.findAll();
    }

    public Medico getMedicoById(int id) {
        return medicoRepository.findById(id);
    }

    public void createMedico(Medico medico) {
        medicoRepository.save(medico);
    }

    public void updateMedico(int id, Medico medico) {
        medicoRepository.update(id, medico);
    }

    public void deleteMedico(int id) {
        medicoRepository.delete(id);
    }
}
