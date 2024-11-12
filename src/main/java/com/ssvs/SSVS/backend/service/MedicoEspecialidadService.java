// src/main/java/com/ssvs/SSVS/backend/service/MedicoEspecialidadService.java
package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.model.MedicoEspecialidad;
import com.ssvs.SSVS.backend.repository.MedicoEspecialidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoEspecialidadService {
    private final MedicoEspecialidadRepository repository;

    public MedicoEspecialidadService(MedicoEspecialidadRepository repository) {
        this.repository = repository;
    }

    public List<MedicoEspecialidad> findAllWithDetails() {
        return repository.findAllWithDetails();
    }

    public List<MedicoEspecialidad> findEspecialidadesByMedicoId(int medicoId) {
        return repository.findEspecialidadesByMedicoId(medicoId);
    }

    public void addEspecialidadToMedico(int medicoId, int especialidadId) {
        repository.addEspecialidadToMedico(medicoId, especialidadId);
    }

    public void removeEspecialidadFromMedico(int medicoId, int especialidadId) {
        repository.removeEspecialidadFromMedico(medicoId, especialidadId);
    }
}
