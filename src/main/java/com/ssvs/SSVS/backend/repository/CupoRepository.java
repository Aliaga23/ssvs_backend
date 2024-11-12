package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.model.Cupo;
import com.ssvs.SSVS.backend.dto.CupoInfoDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CupoRepository {
    private final JdbcTemplate jdbcTemplate;

    public CupoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Cupo> rowMapper = new RowMapper<>() {
        @Override
        public Cupo mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Cupo(
                rs.getInt("id"),
                rs.getInt("horario_medico_especialidad_id"),
                rs.getString("hora_inicio"),
                rs.getString("hora_final"),
                rs.getString("estado")  // Nuevo campo estado
            );
        }
    };

    public List<CupoInfoDTO> findCuposByMedicoAndEspecialidad(int medicoId, int especialidadId) {
        String sql = """
            SELECT 
                hme.horario_medico_especialidad_id as horario_medico_especialidad_id,
                c.id AS cupo_id,
                c.hora_inicio,
                c.hora_final,
                c.estado,  -- Incluye estado
                h.dia_id,
                d.dia_semana,
                j.nombre AS jornada,
                j.hora_inicio AS jornada_inicio,
                j.hora_final AS jornada_final,
                m.medico_id,
                u.nombre AS nombre_medico,
                u.apellido AS apellido_medico,
                e.especialidad_id,
                e.nombre AS nombre_especialidad,
                NOT EXISTS (
                    SELECT 1 FROM Reservas r WHERE r.cupo_id = c.id
                ) AS disponible
            FROM CUPO c
            JOIN Horario_Medico_Especialidad hme ON c.horario_medico_especialidad_id = hme.horario_medico_especialidad_id
            JOIN Medicos m ON hme.medico_id = m.medico_id
            JOIN Usuarios u ON m.usuario_id = u.usuario_id
            JOIN Especialidades e ON hme.especialidad_id = e.especialidad_id
            JOIN HORARIOS h ON hme.horario_id = h.id
            JOIN DIA d ON h.dia_id = d.id
            JOIN JORNADA j ON h.jornada_id = j.id
            WHERE hme.medico_id = ? AND hme.especialidad_id = ?
        """;

        return jdbcTemplate.query(sql, new RowMapper<>() {
            @Override
            public CupoInfoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                CupoInfoDTO cupo = new CupoInfoDTO();
                cupo.setHorarioMedicoEspecialidadId(rs.getInt("horario_medico_especialidad_id"));
                cupo.setCupoId(rs.getInt("cupo_id"));
                cupo.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
                cupo.setHoraFin(rs.getTime("hora_final").toLocalTime());
                cupo.setDiaId(rs.getInt("dia_id"));
                cupo.setDiaSemana(rs.getString("dia_semana"));
                cupo.setJornadaNombre(rs.getString("jornada"));
                cupo.setJornadaInicio(rs.getTime("jornada_inicio").toLocalTime());
                cupo.setJornadaFin(rs.getTime("jornada_final").toLocalTime());
                cupo.setMedicoId(rs.getInt("medico_id"));
                cupo.setNombreMedico(rs.getString("nombre_medico"));
                cupo.setApellidoMedico(rs.getString("apellido_medico"));
                cupo.setEspecialidadId(rs.getInt("especialidad_id"));
                cupo.setNombreEspecialidad(rs.getString("nombre_especialidad"));
                cupo.setDisponible(rs.getBoolean("disponible"));
                cupo.setEstado(rs.getString("estado"));  // Asigna el valor de estado
                return cupo;
            }
        }, medicoId, especialidadId);
    }

    // Obtener todos los cupos
    public List<Cupo> findAll() {
        String sql = "SELECT * FROM CUPO";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Filtrar cupo por id
    public Cupo findById(int id) {
        String sql = "SELECT * FROM CUPO WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
