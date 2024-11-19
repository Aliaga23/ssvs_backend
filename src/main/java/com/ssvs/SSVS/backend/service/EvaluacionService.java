package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.dto.EvaluacionDTO;
import com.ssvs.SSVS.backend.repository.EvaluacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;

    public EvaluacionService(EvaluacionRepository evaluacionRepository) {
        this.evaluacionRepository = evaluacionRepository;
    }

    public void guardarEvaluacion(EvaluacionDTO evaluacion) {
        evaluacionRepository.guardarEvaluacion(evaluacion);
    }

    public List<EvaluacionDTO> obtenerEvaluacionesPorMedicoYEspecialidad(int medicoId, int especialidadId) {
        return evaluacionRepository.obtenerEvaluacionesPorMedicoYEspecialidad(medicoId, especialidadId);
    }

    public Double obtenerPromedioEvaluacion(int medicoId, int especialidadId) {
        Double promedio = evaluacionRepository.obtenerPromedioEvaluacion(medicoId, especialidadId);
        return promedio != null ? promedio : 0.0;
    }
    public List<EvaluacionDTO> obtenerTodasLasEvaluaciones() {
        return evaluacionRepository.obtenerTodasLasEvaluaciones();
    }
    
}
