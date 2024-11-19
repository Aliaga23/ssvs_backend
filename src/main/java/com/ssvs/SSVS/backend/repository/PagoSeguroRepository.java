package com.ssvs.SSVS.backend.repository;
import com.ssvs.SSVS.backend.dto.PagoSeguroDTO;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class PagoSeguroRepository {

    private final JdbcTemplate jdbcTemplate;

    public PagoSeguroRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<PagoSeguroDTO> rowMapper = (rs, rowNum) -> {
        PagoSeguroDTO pago = new PagoSeguroDTO();
        pago.setPagoId(rs.getInt("pago_id"));
        pago.setPacienteId(rs.getInt("paciente_id"));
        pago.setMonto(rs.getDouble("monto"));
        pago.setFechaPago(rs.getDate("fecha_pago").toLocalDate());
        pago.setFechaVencimiento(rs.getDate("fecha_vencimiento") != null
                ? rs.getDate("fecha_vencimiento").toLocalDate()
                : null);
        pago.setMetodoId(rs.getInt("metodo_id"));
        pago.setMetodoPago(rs.getString("metodo_pago"));
        pago.setEstado(rs.getString("estado"));
        return pago;
    };

    public void registrarPagoSeguro(int pacienteId, double monto, LocalDate fechaPago, int metodoId, String estado) {
        String sql = """
            INSERT INTO PagosSeguros (paciente_id, monto, fecha_pago, metodo_id, estado)
            VALUES (?, ?, ?, ?, ?)
        """;
        jdbcTemplate.update(sql, pacienteId, monto, fechaPago, metodoId, estado);
    }

    public List<PagoSeguroDTO> getHistorialDePagos(int pacienteId) {
        String sql = """
            SELECT ps.*, mp.nombre AS metodo_pago
            FROM PagosSeguros ps
            LEFT JOIN MetodoDePago mp ON ps.metodo_id = mp.metodo_id
            WHERE paciente_id = ? ORDER BY fecha_pago DESC
        """;
        return jdbcTemplate.query(sql, rowMapper, pacienteId);
    }

    public LocalDate obtenerFechaVencimiento(int pacienteId) {
        String sql = "SELECT MAX(fecha_vencimiento) FROM PagosSeguros WHERE paciente_id = ?";
        return jdbcTemplate.queryForObject(sql, LocalDate.class, pacienteId);
    }
}
