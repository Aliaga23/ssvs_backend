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
        String sql = """
            SELECT m.medico_id, m.usuario_id, m.genero, m.fecha_nacimiento, 
                   u.nombre AS nombre, u.apellido AS apellido
            FROM Medicos m
            JOIN Usuarios u ON m.usuario_id = u.usuario_id
        """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Medico medico = new Medico();
            medico.setId(rs.getInt("medico_id"));
            medico.setUsuarioId(rs.getInt("usuario_id"));
            medico.setGenero(rs.getString("genero"));
            medico.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
            medico.setNombre(rs.getString("nombre"));
            medico.setApellido(rs.getString("apellido"));
            return medico;
        });
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
