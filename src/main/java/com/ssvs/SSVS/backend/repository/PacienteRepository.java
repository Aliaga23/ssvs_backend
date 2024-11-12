package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.model.Paciente;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PacienteRepository {

    private final JdbcTemplate jdbcTemplate;

    public PacienteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Paciente> rowMapper = new RowMapper<Paciente>() {
        @Override
        public Paciente mapRow(ResultSet rs, int rowNum) throws SQLException {
            Paciente paciente = new Paciente();
            paciente.setId(rs.getInt("paciente_id"));
            paciente.setUsuarioId(rs.getInt("usuario_id"));
            paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
            paciente.setGenero(rs.getString("genero"));
            paciente.setTipoSangre(rs.getString("tipo_sangre"));  // Obtener tipo de sangre
            return paciente;
        }
    };

    public List<Paciente> findAll() {
        String sql = "SELECT * FROM Pacientes";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Paciente findById(int id) {
        String sql = "SELECT * FROM Pacientes WHERE paciente_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void save(Paciente paciente) {
        String sql = "INSERT INTO Pacientes (usuario_id, fecha_nacimiento, genero, tipo_sangre) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, paciente.getUsuarioId(), paciente.getFechaNacimiento(), paciente.getGenero(), paciente.getTipoSangre());
    }

    public void update(int id, Paciente paciente) {
        String sql = "UPDATE Pacientes SET usuario_id = ?, fecha_nacimiento = ?, genero = ?, tipo_sangre = ? WHERE paciente_id = ?";
        jdbcTemplate.update(sql, paciente.getUsuarioId(), paciente.getFechaNacimiento(), paciente.getGenero(), paciente.getTipoSangre(), id);
    }

    public void delete(int id) {
        String sql = "DELETE FROM Pacientes WHERE paciente_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
