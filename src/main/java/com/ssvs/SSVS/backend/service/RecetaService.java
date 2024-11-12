package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.model.Receta;
import com.ssvs.SSVS.backend.dto.RecetaDetallesDTO;
import com.ssvs.SSVS.backend.repository.RecetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetaService {

    private final RecetaRepository recetaRepository;

    public RecetaService(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    public List<Receta> getAllRecetas() {
        return recetaRepository.findAll();
    }

    public Receta getRecetaById(int id) {
        return recetaRepository.findById(id);
    }

    public int createReceta(Receta receta) {
        return recetaRepository.save(receta);
    }

    public void updateReceta(int id, Receta receta) {
        recetaRepository.update(id, receta);
    }

 // Cambia el tipo de retorno en el servicio a List<RecetaDetallesDTO>
public List<RecetaDetallesDTO> getRecetaDetallesByConsultaId(int consultaId) {
    return recetaRepository.getRecetaDetallesByConsultaId(consultaId);
}


    public void deleteReceta(int id) {
        recetaRepository.delete(id);
    }
}
