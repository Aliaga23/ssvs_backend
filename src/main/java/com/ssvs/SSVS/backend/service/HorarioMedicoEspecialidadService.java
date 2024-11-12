// src/main/java/com/ssvs/SSVS/backend/service/HorarioMedicoEspecialidadService.java
package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.model.HorarioMedicoEspecialidad;
import com.ssvs.SSVS.backend.repository.HorarioMedicoEspecialidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioMedicoEspecialidadService {

    private final HorarioMedicoEspecialidadRepository repository;

    public HorarioMedicoEspecialidadService(HorarioMedicoEspecialidadRepository repository) {
        this.repository = repository;
    }

    public List<HorarioMedicoEspecialidad> findAll() {
        return repository.findAll();
    }
    public List<HorarioMedicoEspecialidad> findMedicosByEspecialidadId(int especialidadId) {
        return repository.findMedicosByEspecialidadId(especialidadId);
    }
    public HorarioMedicoEspecialidad findById(int id) {
        return repository.findById(id);
    }

    public void save(HorarioMedicoEspecialidad hme) {
         repository.save(hme);
       
    }

    public void update(int id, HorarioMedicoEspecialidad hme) {
        repository.update(id, hme);
    }

    public void delete(int id) {
        repository.delete(id);
    }
}
