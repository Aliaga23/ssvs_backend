package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.model.Medicamento;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MedicamentoRepository {

    private final JdbcTemplate jdbcTemplate;

    public MedicamentoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Medicamento> rowMapper = new RowMapper<Medicamento>() {
        @Override
        public Medicamento mapRow(ResultSet rs, int rowNum) throws SQLException {
            Medicamento medicamento = new Medicamento();
            medicamento.setId(rs.getInt("medicamento_id"));
            medicamento.setNombre(rs.getString("nombre"));
            medicamento.setDescripcion(rs.getString("descripcion"));
            return medicamento;
        }
    };

    public List<Medicamento> findAll() {
        String sql = "SELECT * FROM Medicamentos";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Medicamento findById(int id) {
        String sql = "SELECT * FROM Medicamentos WHERE medicamento_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void save(Medicamento medicamento) {
        String sql = "INSERT INTO Medicamentos (nombre, descripcion) VALUES (?, ?)";
        jdbcTemplate.update(sql, medicamento.getNombre(), medicamento.getDescripcion());
    }

    public void update(int id, Medicamento medicamento) {
        String sql = "UPDATE Medicamentos SET nombre = ?, descripcion = ? WHERE medicamento_id = ?";
        jdbcTemplate.update(sql, medicamento.getNombre(), medicamento.getDescripcion(), id);
    }

    public void delete(int id) {
        String sql = "DELETE FROM Medicamentos WHERE medicamento_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
