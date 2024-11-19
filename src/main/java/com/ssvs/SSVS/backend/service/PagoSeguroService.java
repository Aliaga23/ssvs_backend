package com.ssvs.SSVS.backend.service;
import com.ssvs.SSVS.backend.dto.PagoSeguroDTO;

import com.ssvs.SSVS.backend.repository.PagoSeguroRepository;
import org.springframework.stereotype.Service;
import java.util.List;

import java.time.LocalDate;

@Service
public class PagoSeguroService {

    private final PagoSeguroRepository pagoSeguroRepository;
    private final PacienteService pacienteService;

    public PagoSeguroService(PagoSeguroRepository pagoSeguroRepository, PacienteService pacienteService) {
        this.pagoSeguroRepository = pagoSeguroRepository;
        this.pacienteService = pacienteService;
    }

    // Validar y actualizar el estado del seguro de un paciente
    public void actualizarEstadoSeguro(int pacienteId) {
        LocalDate fechaVencimiento = pagoSeguroRepository.obtenerFechaVencimiento(pacienteId);

        if (fechaVencimiento != null && fechaVencimiento.isAfter(LocalDate.now())) {
            pacienteService.actualizarEstadoSeguro(pacienteId, true, fechaVencimiento);
        } else {
            pacienteService.actualizarEstadoSeguro(pacienteId, false, null);
        }
    }

    // Registrar un nuevo pago de seguro
    public void registrarPagoSeguro(int pacienteId, double monto, LocalDate fechaVencimiento, String metodoPago) {
        pagoSeguroRepository.registrarPagoSeguro(pacienteId, monto, fechaVencimiento, metodoPago);
        actualizarEstadoSeguro(pacienteId);
    }

    // Validar si el seguro está activo
    public boolean validarEstadoSeguro(int pacienteId) {
        LocalDate fechaVencimiento = pagoSeguroRepository.obtenerFechaVencimiento(pacienteId);
        return fechaVencimiento != null && fechaVencimiento.isAfter(LocalDate.now());
    }

    public List<PagoSeguroDTO> obtenerHistorialDePagos(int pacienteId) {
        return pagoSeguroRepository.getHistorialDePagos(pacienteId);
    }
}
