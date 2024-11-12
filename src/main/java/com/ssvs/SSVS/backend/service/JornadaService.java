package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.model.Jornada;
import com.ssvs.SSVS.backend.repository.JornadaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JornadaService {

    private final JornadaRepository jornadaRepository;

    public JornadaService(JornadaRepository jornadaRepository) {
        this.jornadaRepository = jornadaRepository;
    }

    public List<Jornada> getAllJornadas() {
        return jornadaRepository.findAll();
    }

    public Jornada getJornadaById(int id) {
        return jornadaRepository.findById(id);
    }

    public int createJornada(Jornada jornada) {
        return jornadaRepository.save(jornada);
    }

    public int updateJornada(int id, Jornada jornada) {
        return jornadaRepository.update(id, jornada);
    }

    public int deleteJornada(int id) {
        return jornadaRepository.delete(id);
    }
}
