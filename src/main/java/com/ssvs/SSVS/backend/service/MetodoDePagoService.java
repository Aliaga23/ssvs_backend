package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.model.MetodoDePago;
import com.ssvs.SSVS.backend.repository.MetodoDePagoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetodoDePagoService {

    private final MetodoDePagoRepository metodoDePagoRepository;

    public MetodoDePagoService(MetodoDePagoRepository metodoDePagoRepository) {
        this.metodoDePagoRepository = metodoDePagoRepository;
    }

    // Obtener todos los métodos de pago
    public List<MetodoDePago> getAllMetodosDePago() {
        return metodoDePagoRepository.findAll();
    }

    // Obtener un método de pago por ID
    public MetodoDePago getMetodoDePagoById(int id) {
        return metodoDePagoRepository.findById(id);
    }

    // Crear un nuevo método de pago
    public void createMetodoDePago(MetodoDePago metodoDePago) {
        metodoDePagoRepository.save(metodoDePago);
    }

    // Actualizar un método de pago
    public void updateMetodoDePago(int id, MetodoDePago metodoDePago) {
        metodoDePagoRepository.update(id, metodoDePago);
    }

    // Eliminar un método de pago
    public void deleteMetodoDePago(int id) {
        metodoDePagoRepository.delete(id);
    }
}
