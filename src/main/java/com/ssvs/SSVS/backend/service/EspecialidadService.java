package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.model.Especialidad;
import com.ssvs.SSVS.backend.repository.EspecialidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadService {

    private final EspecialidadRepository especialidadRepository;

    public EspecialidadService(EspecialidadRepository especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }

    public List<Especialidad> getAllEspecialidades() {
        return especialidadRepository.findAll();
    }

    public Especialidad getEspecialidadById(int id) {
        return especialidadRepository.findById(id);
    }

    public void createEspecialidad(Especialidad especialidad) {
        especialidadRepository.save(especialidad);
    }

    public void updateEspecialidad(int id, Especialidad especialidad) {
        especialidadRepository.update(id, especialidad);
    }

    public void deleteEspecialidad(int id) {
        especialidadRepository.delete(id);
    }
}
