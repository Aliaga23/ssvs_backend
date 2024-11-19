package com.ssvs.SSVS.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bitacora")
public class BitacoraController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Obtiene el historial completo de la bitácora, ordenado por fecha descendente.
     * 
     * @return ResponseEntity con la lista de registros de la bitácora.
     */
    @GetMapping("/historial")
    public ResponseEntity<List<Map<String, Object>>> obtenerHistorial() {
        try {
            String sql = "SELECT * FROM Bitacora ORDER BY fecha DESC";
            List<Map<String, Object>> bitacora = jdbcTemplate.queryForList(sql);
            return ResponseEntity.ok(bitacora);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Devuelve un error 500 si ocurre algún problema
        }
    }
}
