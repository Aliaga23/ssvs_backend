package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.model.Permiso;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PermisoRepository {

    private final JdbcTemplate jdbcTemplate;

    public PermisoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Mapeo de los resultados SQL a un objeto Permiso
    private RowMapper<Permiso> rowMapper = new RowMapper<Permiso>() {
        @Override
        public Permiso mapRow(ResultSet rs, int rowNum) throws SQLException {
            Permiso permiso = new Permiso();
            permiso.setId(rs.getInt("permiso_id"));
            permiso.setNombre(rs.getString("nombre"));
            permiso.setDescripcion(rs.getString("descripcion"));
            return permiso;
        }
    };

    // Obtener todos los permisos
    public List<Permiso> findAll() {
        String sql = "SELECT * FROM Permisos";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Buscar un permiso por ID
    public Permiso findById(int id) {
        String sql = "SELECT * FROM Permisos WHERE permiso_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    // Crear un nuevo permiso
    public void save(Permiso permiso) {
        String sql = "INSERT INTO Permisos (nombre, descripcion) VALUES (?, ?)";
        jdbcTemplate.update(sql, permiso.getNombre(), permiso.getDescripcion());
    }

    // Actualizar un permiso
    public void update(int id, Permiso permiso) {
        String sql = "UPDATE Permisos SET nombre = ?, descripcion = ? WHERE permiso_id = ?";
        jdbcTemplate.update(sql, permiso.getNombre(), permiso.getDescripcion(), id);
    }

    // Eliminar un permiso
    public void delete(int id) {
        String sql = "DELETE FROM Permisos WHERE permiso_id = ?";
        jdbcTemplate.update(sql, id);
    }
}