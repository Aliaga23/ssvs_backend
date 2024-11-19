package com.ssvs.SSVS.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SesionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void establecerSesion(int usuarioId, String ip) {
        String sqlUsuario = "SET SESSION \"usuario.id\" = " + usuarioId;
        jdbcTemplate.execute(sqlUsuario);

        String sqlIp = "SET SESSION \"usuario.ip\" = '" + ip + "'";
        jdbcTemplate.execute(sqlIp);
    }

    public int obtenerUsuarioIdSesion() {
        try {
            String sql = "SHOW \"usuario.id\"";
            return Integer.parseInt(jdbcTemplate.queryForObject(sql, String.class));
        } catch (Exception e) {
            throw new RuntimeException("No se pudo obtener el usuarioId de la sesión: " + e.getMessage());
        }
    }
}
