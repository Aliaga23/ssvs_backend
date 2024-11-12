// src/main/java/com/ssvs/SSVS/backend/service/HorarioService.java
package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.model.Horario;
import com.ssvs.SSVS.backend.repository.HorarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioService {

    private final HorarioRepository horarioRepository;

    public HorarioService(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    public List<Horario> getAllHorarios() {
        return horarioRepository.findAll();
    }

    public Horario getHorarioById(int id) {
        return horarioRepository.findById(id);
    }

    public void createHorario(Horario horario) {
        horarioRepository.save(horario);
    }

    public void updateHorario(int id, Horario horario) {
        horarioRepository.update(id, horario);
    }

    public void deleteHorario(int id) {
        horarioRepository.delete(id);
    }
}
