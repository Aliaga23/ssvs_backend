package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.model.Receta;
import com.ssvs.SSVS.backend.dto.RecetaDetallesDTO;
import com.ssvs.SSVS.backend.service.RecetaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    private final RecetaService recetaService;

    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @GetMapping
    public List<Receta> getAllRecetas() {
        return recetaService.getAllRecetas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> getRecetaById(@PathVariable int id) {
        Receta receta = recetaService.getRecetaById(id);
        return receta != null ? ResponseEntity.ok(receta) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Receta> createReceta(@RequestBody Receta receta) {
        int recetaId = recetaService.createReceta(receta);
        receta.setId(recetaId);
        return ResponseEntity.ok(receta); // Esto devuelve el objeto receta con el ID generado
    }
    


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReceta(@PathVariable int id, @RequestBody Receta receta) {
        if (recetaService.getRecetaById(id) != null) {
            recetaService.updateReceta(id, receta);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/detalles/{consultaId}")
    public ResponseEntity<List<RecetaDetallesDTO>> getRecetaDetallesByConsultaId(@PathVariable int consultaId) {
        List<RecetaDetallesDTO> recetaDetalles = recetaService.getRecetaDetallesByConsultaId(consultaId);
        return recetaDetalles != null ? ResponseEntity.ok(recetaDetalles) : ResponseEntity.notFound().build();
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceta(@PathVariable int id) {
        if (recetaService.getRecetaById(id) != null) {
            recetaService.deleteReceta(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
