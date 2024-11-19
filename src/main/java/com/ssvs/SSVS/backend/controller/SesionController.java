package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.service.SesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sesion")
public class SesionController {
    @Autowired
    private SesionService sesionService;

    // DTO para capturar los datos del cuerpo de la solicitud
    public static class SesionDTO {
        private int usuarioId;
        private String ip;

        // Getters y Setters
        public int getUsuarioId() {
            return usuarioId;
        }

        public void setUsuarioId(int usuarioId) {
            this.usuarioId = usuarioId;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }
    }

    @PostMapping("/set")
    public ResponseEntity<String> establecerSesion(@RequestBody SesionDTO sesionDTO) {
        try {
            sesionService.establecerSesion(sesionDTO.getUsuarioId(), sesionDTO.getIp());
            return ResponseEntity.ok("Sesión configurada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Error al configurar la sesión: " + e.getMessage());
        }
    }
    /**
     * Verificar los valores de sesión configurados
     * @return Valores de sesión o mensaje de error
     */
    @GetMapping("/check")
    public ResponseEntity<String> verificarSesion() {
        try {
            String resultado = sesionService.obtenerSesion();
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Error al verificar la sesión: " + e.getMessage());
        }
    }
}
