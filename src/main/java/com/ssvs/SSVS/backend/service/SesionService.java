package com.ssvs.SSVS.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SesionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void establecerSesion(int usuarioId, String ip) {
        try {
            // Establecer la variable de sesión para usuario.id
            String sqlUsuario = "SET SESSION \"usuario.id\" = " + usuarioId;
            jdbcTemplate.execute(sqlUsuario);

            // Establecer la variable de sesión para usuario.ip
            String sqlIp = "SET SESSION \"usuario.ip\" = '" + ip + "'";
            jdbcTemplate.execute(sqlIp);

            System.out.println("Sesión configurada: usuario.id = " + usuarioId + ", usuario.ip = " + ip);
        } catch (Exception e) {
            System.err.println("Error configurando las variables de sesión: " + e.getMessage());
            throw e;
        }
    }

    public String obtenerSesion() {
        try {
            // Consultar las variables de sesión configuradas
            String sqlUsuario = "SHOW \"usuario.id\"";
            String sqlIp = "SHOW \"usuario.ip\"";

            String usuarioId = jdbcTemplate.queryForObject(sqlUsuario, String.class);
            String ip = jdbcTemplate.queryForObject(sqlIp, String.class);

            return "Usuario ID: " + usuarioId + ", IP: " + ip;
        } catch (Exception e) {
            return "Error obteniendo las variables de sesión: " + e.getMessage();
        }
    }
}
