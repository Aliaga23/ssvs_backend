package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.model.Rol;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RolRepository {

    private final JdbcTemplate jdbcTemplate;

    public RolRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Mapeo de los resultados SQL a un objeto Rol
    private RowMapper<Rol> rowMapper = new RowMapper<Rol>() {
        @Override
        public Rol mapRow(ResultSet rs, int rowNum) throws SQLException {
            Rol rol = new Rol();
            rol.setId(rs.getInt("rol_id"));
            rol.setNombre(rs.getString("nombre"));
            return rol;
        }
    };

    // Obtener todos los roles
    public List<Rol> findAll() {
        String sql = "SELECT rol_id, nombre FROM Roles";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Buscar un rol por ID
    public Rol findById(int id) {
        String sql = "SELECT rol_id, nombre FROM Roles WHERE rol_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    // Crear un nuevo rol
    public void save(Rol rol) {
        String sql = "INSERT INTO Roles (nombre) VALUES (?)";
        jdbcTemplate.update(sql, rol.getNombre());
    }

    // Actualizar un rol
    public void update(int id, Rol rol) {
        String sql = "UPDATE Roles SET nombre = ? WHERE rol_id = ?";
        jdbcTemplate.update(sql, rol.getNombre(), id);
    }

    // Eliminar un rol
    public void delete(int id) {
        String sql = "DELETE FROM Roles WHERE rol_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
