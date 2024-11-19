package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.service.IPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ip")
public class IPController {

    @Autowired
    private IPService ipService;

    @GetMapping("/publica")
    public ResponseEntity<String> obtenerIPPublica() {
        String ip = ipService.obtenerIPPublica();
        if ("No se pudo obtener la IP pública".equals(ip)) {
            return ResponseEntity.status(500).body(ip);
        }
        return ResponseEntity.ok(ip);
    }
}
