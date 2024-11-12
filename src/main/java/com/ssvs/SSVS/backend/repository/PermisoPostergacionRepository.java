package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.model.PermisoPostergacion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PermisoPostergacionRepository {

    private final JdbcTemplate jdbcTemplate;

    public PermisoPostergacionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PermisoPostergacion> rowMapper = new RowMapper<PermisoPostergacion>() {
        @Override
        public PermisoPostergacion mapRow(ResultSet rs, int rowNum) throws SQLException {
            PermisoPostergacion permiso = new PermisoPostergacion();
            permiso.setPostergacionId(rs.getInt("postergacion_id"));
            permiso.setMedicoId(rs.getInt("medico_id"));
            permiso.setTipoPostergacion(rs.getString("tipo_postergacion"));
            permiso.setFechaSolicitud(rs.getDate("fecha_solicitud"));
            permiso.setFechaInicio(rs.getDate("fecha_inicio"));
            permiso.setHoraInicio(rs.getTime("hora_inicio"));
            permiso.setHoraFin(rs.getTime("hora_fin"));
            permiso.setEstado(rs.getString("estado"));
            permiso.setMotivo(rs.getString("motivo"));
            return permiso;
        }
    };

    public List<PermisoPostergacion> findAll() {
        String sql = "SELECT * FROM Permisos_Postergacion";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public PermisoPostergacion findById(int id) {
        String sql = "SELECT * FROM Permisos_Postergacion WHERE postergacion_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public int save(PermisoPostergacion permiso) {
        String sql = "INSERT INTO Permisos_Postergacion (medico_id, tipo_postergacion, fecha_inicio, hora_inicio, hora_fin, estado, motivo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, permiso.getMedicoId(), permiso.getTipoPostergacion(), permiso.getFechaInicio(), permiso.getHoraInicio(), permiso.getHoraFin(), permiso.getEstado(), permiso.getMotivo());
    }

    public int update(PermisoPostergacion permiso) {
        String sql = "UPDATE Permisos_Postergacion SET estado = ?, motivo = ? WHERE postergacion_id = ?";
        return jdbcTemplate.update(sql, permiso.getEstado(), permiso.getMotivo(), permiso.getPostergacionId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM Permisos_Postergacion WHERE postergacion_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
