// src/main/java/com/ssvs/SSVS/backend/repository/MedicoEspecialidadRepository.java
package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.model.MedicoEspecialidad;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MedicoEspecialidadRepository {

    private final JdbcTemplate jdbcTemplate;

    public MedicoEspecialidadRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<MedicoEspecialidad> rowMapper = new RowMapper<MedicoEspecialidad>() {
        @Override
        public MedicoEspecialidad mapRow(ResultSet rs, int rowNum) throws SQLException {
            MedicoEspecialidad me = new MedicoEspecialidad();
            me.setMedicoId(rs.getInt("medico_id"));
            me.setMedicoNombre(rs.getString("medico_nombre"));
            me.setMedicoApellido(rs.getString("medico_apellido"));
            me.setEspecialidadId(rs.getInt("especialidad_id"));
            me.setEspecialidadNombre(rs.getString("especialidad_nombre"));
            return me;
        }
    };

    public List<MedicoEspecialidad> findAllWithDetails() {
        String sql = "SELECT m.medico_id, u.nombre AS medico_nombre, u.apellido AS medico_apellido, " +
                     "e.especialidad_id, e.nombre AS especialidad_nombre " +
                     "FROM Medicos_Especialidades me " +
                     "JOIN Medicos m ON me.medico_id = m.medico_id " +
                     "JOIN Usuarios u ON m.usuario_id = u.usuario_id " +
                     "JOIN Especialidades e ON me.especialidad_id = e.especialidad_id";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<MedicoEspecialidad> findEspecialidadesByMedicoId(int medicoId) {
        String sql = "SELECT m.medico_id, u.nombre AS medico_nombre, u.apellido AS medico_apellido, " +
                     "e.especialidad_id, e.nombre AS especialidad_nombre " +
                     "FROM Medicos_Especialidades me " +
                     "JOIN Medicos m ON me.medico_id = m.medico_id " +
                     "JOIN Usuarios u ON m.usuario_id = u.usuario_id " +
                     "JOIN Especialidades e ON me.especialidad_id = e.especialidad_id " +
                     "WHERE m.medico_id = ?";
        return jdbcTemplate.query(sql, rowMapper, medicoId);
    }

    public int addEspecialidadToMedico(int medicoId, int especialidadId) {
        String sql = "INSERT INTO Medicos_Especialidades (medico_id, especialidad_id) VALUES (?, ?)";
        return jdbcTemplate.update(sql, medicoId, especialidadId);
    }

    public int removeEspecialidadFromMedico(int medicoId, int especialidadId) {
        String sql = "DELETE FROM Medicos_Especialidades WHERE medico_id = ? AND especialidad_id = ?";
        return jdbcTemplate.update(sql, medicoId, especialidadId);
    }
}
