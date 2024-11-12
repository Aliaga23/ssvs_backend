package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.model.Medico;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MedicoRepository {

    private final JdbcTemplate jdbcTemplate;

    public MedicoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Medico> rowMapper = new RowMapper<Medico>() {
        @Override
        public Medico mapRow(ResultSet rs, int rowNum) throws SQLException {
            Medico medico = new Medico();
            medico.setId(rs.getInt("medico_id"));
            medico.setUsuarioId(rs.getInt("usuario_id"));
            medico.setGenero(rs.getString("genero"));
            medico.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());  // Convertir a LocalDate
            return medico;
        }
    };

    public List<Medico> findAll() {
        String sql = "SELECT * FROM Medicos";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Medico findById(int id) {
        String sql = "SELECT * FROM Medicos WHERE medico_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void save(Medico medico) {
        String sql = "INSERT INTO Medicos (usuario_id, genero, fecha_nacimiento) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, medico.getUsuarioId(), medico.getGenero(), medico.getFechaNacimiento());
    }

    public void update(int id, Medico medico) {
        String sql = "UPDATE Medicos SET usuario_id = ?, genero = ?, fecha_nacimiento = ? WHERE medico_id = ?";
        jdbcTemplate.update(sql, medico.getUsuarioId(), medico.getGenero(), medico.getFechaNacimiento(), id);
    }

    public void delete(int id) {
        String sql = "DELETE FROM Medicos WHERE medico_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
