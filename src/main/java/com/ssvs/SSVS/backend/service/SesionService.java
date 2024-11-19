package com.ssvs.SSVS.backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SesionService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void establecerSesion(int usuarioId, String ip) {
        String sqlUsuario = "SET SESSION \"usuario.id\" = ?";
        String sqlIp = "SET SESSION \"usuario.ip\" = ?";
        jdbcTemplate.update(sqlUsuario, usuarioId);
        jdbcTemplate.update(sqlIp, ip);
    }
}