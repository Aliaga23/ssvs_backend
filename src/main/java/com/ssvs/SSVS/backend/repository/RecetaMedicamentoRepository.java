package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.model.RecetaMedicamento;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RecetaMedicamentoRepository {

    private final JdbcTemplate jdbcTemplate;

    public RecetaMedicamentoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<RecetaMedicamento> rowMapper = new RowMapper<RecetaMedicamento>() {
        @Override
        public RecetaMedicamento mapRow(ResultSet rs, int rowNum) throws SQLException {
            RecetaMedicamento recetaMedicamento = new RecetaMedicamento();
            recetaMedicamento.setRecetaId(rs.getInt("receta_id"));
            recetaMedicamento.setMedicamentoId(rs.getInt("medicamento_id"));
            recetaMedicamento.setDosis(rs.getString("dosis"));
            recetaMedicamento.setFrecuencia(rs.getString("frecuencia"));
            recetaMedicamento.setDuracion(rs.getInt("duracion"));
            return recetaMedicamento;
        }
    };

    public List<RecetaMedicamento> findAll() {
        String sql = "SELECT * FROM Recetas_Medicamentos";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<RecetaMedicamento> findByRecetaId(int recetaId) {
        String sql = "SELECT * FROM Recetas_Medicamentos WHERE receta_id = ?";
        return jdbcTemplate.query(sql, rowMapper, recetaId);
    }

    public void save(RecetaMedicamento recetaMedicamento) {
        String sql = "INSERT INTO Recetas_Medicamentos (receta_id, medicamento_id, dosis, frecuencia, duracion) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, recetaMedicamento.getRecetaId(), recetaMedicamento.getMedicamentoId(),
                recetaMedicamento.getDosis(), recetaMedicamento.getFrecuencia(), recetaMedicamento.getDuracion());
    }

    public void delete(int recetaId, int medicamentoId) {
        String sql = "DELETE FROM Recetas_Medicamentos WHERE receta_id = ? AND medicamento_id = ?";
        jdbcTemplate.update(sql, recetaId, medicamentoId);
    }
}
