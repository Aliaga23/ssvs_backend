package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.model.HorarioMedicoEspecialidad;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HorarioMedicoEspecialidadRepository {

    private final JdbcTemplate jdbcTemplate;

    public HorarioMedicoEspecialidadRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<HorarioMedicoEspecialidad> rowMapper = new RowMapper<>() {
        @Override
        public HorarioMedicoEspecialidad mapRow(ResultSet rs, int rowNum) throws SQLException {
            HorarioMedicoEspecialidad hme = new HorarioMedicoEspecialidad();
            hme.setHorarioMedicoEspecialidadId(rs.getInt("horario_medico_especialidad_id"));
            hme.setMedicoId(rs.getInt("medico_id"));
            hme.setMedicoNombre(rs.getString("medico_nombre"));
            hme.setMedicoApellido(rs.getString("medico_apellido"));
            hme.setEspecialidadId(rs.getInt("especialidad_id"));
            hme.setEspecialidadNombre(rs.getString("especialidad_nombre"));
            hme.setHorarioId(rs.getInt("horario_id"));
            hme.setDiaSemana(rs.getString("dia_semana"));
            hme.setJornadaNombre(rs.getString("jornada_nombre"));
            // Convert java.sql.Time to LocalTime
            hme.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
            hme.setHoraFin(rs.getTime("hora_fin").toLocalTime());
            return hme;
        }
    };

    public List<HorarioMedicoEspecialidad> findAll() {
        String sql = """
            SELECT hme.horario_medico_especialidad_id,
                   m.medico_id, u.nombre AS medico_nombre, u.apellido AS medico_apellido,
                   e.especialidad_id, e.nombre AS especialidad_nombre,
                   h.id AS horario_id, d.dia_semana, j.nombre AS jornada_nombre,
                   j.hora_inicio, j.hora_final AS hora_fin
            FROM Horario_Medico_Especialidad hme
            JOIN Medicos m ON hme.medico_id = m.medico_id
            JOIN Usuarios u ON m.usuario_id = u.usuario_id
            JOIN Especialidades e ON hme.especialidad_id = e.especialidad_id
            JOIN Horarios h ON hme.horario_id = h.id
            JOIN Dia d ON h.dia_id = d.id
            JOIN Jornada j ON h.jornada_id = j.id
            """;
        return jdbcTemplate.query(sql, rowMapper);
    }

    public HorarioMedicoEspecialidad findById(int id) {
        String sql = """
            SELECT hme.horario_medico_especialidad_id,
                   m.medico_id, u.nombre AS medico_nombre, u.apellido AS medico_apellido,
                   e.especialidad_id, e.nombre AS especialidad_nombre,
                   h.id AS horario_id, d.dia_semana, j.nombre AS jornada_nombre,
                   j.hora_inicio, j.hora_final AS hora_fin
            FROM Horario_Medico_Especialidad hme
            JOIN Medicos m ON hme.medico_id = m.medico_id
            JOIN Usuarios u ON m.usuario_id = u.usuario_id
            JOIN Especialidades e ON hme.especialidad_id = e.especialidad_id
            JOIN Horarios h ON hme.horario_id = h.id
            JOIN Dia d ON h.dia_id = d.id
            JOIN Jornada j ON h.jornada_id = j.id
            WHERE hme.horario_medico_especialidad_id = ?
            """;
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public List<HorarioMedicoEspecialidad> findMedicosByEspecialidadId(int especialidadId) {
        String sql = """
            SELECT hme.horario_medico_especialidad_id,
                   m.medico_id, u.nombre AS medico_nombre, u.apellido AS medico_apellido,
                   e.especialidad_id, e.nombre AS especialidad_nombre,
                   h.id AS horario_id, d.dia_semana, j.nombre AS jornada_nombre,
                   j.hora_inicio, j.hora_final AS hora_fin
            FROM Horario_Medico_Especialidad hme
            JOIN Medicos m ON hme.medico_id = m.medico_id
            JOIN Usuarios u ON m.usuario_id = u.usuario_id
            JOIN Especialidades e ON hme.especialidad_id = e.especialidad_id
            JOIN Horarios h ON hme.horario_id = h.id
            JOIN Dia d ON h.dia_id = d.id
            JOIN Jornada j ON h.jornada_id = j.id
            WHERE hme.especialidad_id = ?
            """;
        return jdbcTemplate.query(sql, rowMapper, especialidadId);
    }
    public int save(HorarioMedicoEspecialidad hme) {
        String sql = """
            INSERT INTO Horario_Medico_Especialidad (medico_id, especialidad_id, horario_id)
            VALUES (?, ?, ?)
            """;
        return jdbcTemplate.update(sql, hme.getMedicoId(), hme.getEspecialidadId(), hme.getHorarioId());
    }

    public int update(int id, HorarioMedicoEspecialidad hme) {
        String sql = """
            UPDATE Horario_Medico_Especialidad
            SET medico_id = ?, especialidad_id = ?, horario_id = ?
            WHERE horario_medico_especialidad_id = ?
            """;
        return jdbcTemplate.update(sql, hme.getMedicoId(), hme.getEspecialidadId(), hme.getHorarioId(), id);
    }

    public int delete(int id) {
        String sql = "DELETE FROM Horario_Medico_Especialidad WHERE horario_medico_especialidad_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
