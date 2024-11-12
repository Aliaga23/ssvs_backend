package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.model.Especialidad;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EspecialidadRepository {

    private final JdbcTemplate jdbcTemplate;

    public EspecialidadRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Especialidad> rowMapper = new RowMapper<Especialidad>() {
        @Override
        public Especialidad mapRow(ResultSet rs, int rowNum) throws SQLException {
            Especialidad especialidad = new Especialidad();
            especialidad.setId(rs.getInt("especialidad_id"));
            especialidad.setNombre(rs.getString("nombre"));
            especialidad.setDescripcion(rs.getString("descripcion"));
            return especialidad;
        }
    };

    public List<Especialidad> findAll() {
        String sql = "SELECT * FROM Especialidades";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Especialidad findById(int id) {
        String sql = "SELECT * FROM Especialidades WHERE especialidad_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void save(Especialidad especialidad) {
        String sql = "INSERT INTO Especialidades (nombre, descripcion) VALUES (?, ?)";
        jdbcTemplate.update(sql, especialidad.getNombre(), especialidad.getDescripcion());
    }

    public void update(int id, Especialidad especialidad) {
        String sql = "UPDATE Especialidades SET nombre = ?, descripcion = ? WHERE especialidad_id = ?";
        jdbcTemplate.update(sql, especialidad.getNombre(), especialidad.getDescripcion(), id);
    }

    public void delete(int id) {
        String sql = "DELETE FROM Especialidades WHERE especialidad_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
