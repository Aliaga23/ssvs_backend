package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.model.RolPermisos;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RolPermisosRepository {

    private final JdbcTemplate jdbcTemplate;

    public RolPermisosRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<RolPermisos> rowMapper = new RowMapper<RolPermisos>() {
        @Override
        public RolPermisos mapRow(ResultSet rs, int rowNum) throws SQLException {
            RolPermisos rolPermisos = new RolPermisos();
            rolPermisos.setRolId(rs.getInt("rol_id"));
            rolPermisos.setPermisoId(rs.getInt("permiso_id"));
            return rolPermisos;
        }
    };

    // Obtener todos los permisos de todos los roles
    public List<RolPermisos> findAll() {
        String sql = "SELECT * FROM Roles_Permisos";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Obtener permisos de un rol específico
    public List<RolPermisos> findByRolId(int rolId) {
        String sql = "SELECT * FROM Roles_Permisos WHERE rol_id = ?";
        return jdbcTemplate.query(sql, rowMapper, rolId);
    }

    // Añadir permiso a un rol
    public void save(RolPermisos rolPermisos) {
        String sql = "INSERT INTO Roles_Permisos (rol_id, permiso_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, rolPermisos.getRolId(), rolPermisos.getPermisoId());
    }

    // Eliminar un permiso de un rol
    public void delete(int rolId, int permisoId) {
        String sql = "DELETE FROM Roles_Permisos WHERE rol_id = ? AND permiso_id = ?";
        jdbcTemplate.update(sql, rolId, permisoId);
    }
}
