package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.model.Dia;
import com.ssvs.SSVS.backend.repository.DiaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaService {

    private final DiaRepository diaRepository;

    public DiaService(DiaRepository diaRepository) {
        this.diaRepository = diaRepository;
    }

    public List<Dia> getAllDias() {
        return diaRepository.findAll();
    }

    public Dia getDiaById(int id) {
        return diaRepository.findById(id);
    }

    public int createDia(Dia dia) {
        return diaRepository.save(dia);
    }

    public int updateDia(int id, Dia dia) {
        return diaRepository.update(id, dia);
    }

    public int deleteDia(int id) {
        return diaRepository.delete(id);
    }
}
