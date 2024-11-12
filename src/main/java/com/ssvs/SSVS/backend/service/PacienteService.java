package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.model.Paciente;
import com.ssvs.SSVS.backend.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    public Paciente getPacienteById(int id) {
        return pacienteRepository.findById(id);
    }

    public void createPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    public void updatePaciente(int id, Paciente paciente) {
        pacienteRepository.update(id, paciente);
    }

    public void deletePaciente(int id) {
        pacienteRepository.delete(id);
    }
    
}
