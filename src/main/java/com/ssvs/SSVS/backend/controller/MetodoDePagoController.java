package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.model.MetodoDePago;
import com.ssvs.SSVS.backend.service.MetodoDePagoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metodos-de-pago")
public class MetodoDePagoController {

    private final MetodoDePagoService metodoDePagoService;

    public MetodoDePagoController(MetodoDePagoService metodoDePagoService) {
        this.metodoDePagoService = metodoDePagoService;
    }

    // Obtener todos los métodos de pago
    @GetMapping
    public List<MetodoDePago> getAllMetodosDePago() {
        return metodoDePagoService.getAllMetodosDePago();
    }

    // Obtener un método de pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<MetodoDePago> getMetodoDePagoById(@PathVariable int id) {
        MetodoDePago metodoDePago = metodoDePagoService.getMetodoDePagoById(id);
        if (metodoDePago != null) {
            return ResponseEntity.ok(metodoDePago);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo método de pago
    @PostMapping
    public void createMetodoDePago(@RequestBody MetodoDePago metodoDePago) {
        metodoDePagoService.createMetodoDePago(metodoDePago);
    }

    // Actualizar un método de pago
    @PutMapping("/{id}")
    public void updateMetodoDePago(@PathVariable int id, @RequestBody MetodoDePago metodoDePago) {
        metodoDePagoService.updateMetodoDePago(id, metodoDePago);
    }

    // Eliminar un método de pago
    @DeleteMapping("/{id}")
    public void deleteMetodoDePago(@PathVariable int id) {
        metodoDePagoService.deleteMetodoDePago(id);
    }
}
