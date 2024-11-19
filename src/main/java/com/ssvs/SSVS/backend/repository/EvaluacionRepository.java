package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.dto.EvaluacionDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EvaluacionRepository {

    private final JdbcTemplate jdbcTemplate;

    public EvaluacionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void guardarEvaluacion(EvaluacionDTO evaluacion) {
        String sql = "INSERT INTO Evaluaciones (consulta_id, paciente_id, medico_id, especialidad_id, puntuacion, comentario) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, evaluacion.getConsultaId(), evaluacion.getPacienteId(),
                            evaluacion.getMedicoId(), evaluacion.getEspecialidadId(),
                            evaluacion.getPuntuacion(), evaluacion.getComentario());
    }

    public List<EvaluacionDTO> obtenerEvaluacionesPorMedicoYEspecialidad(int medicoId, int especialidadId) {
        String sql = "SELECT * FROM Evaluaciones WHERE medico_id = ? AND especialidad_id = ?";
        return jdbcTemplate.query(sql, evaluacionRowMapper(), medicoId, especialidadId);
    }

    public Double obtenerPromedioEvaluacion(int medicoId, int especialidadId) {
        String sql = "SELECT AVG(puntuacion) FROM Evaluaciones WHERE medico_id = ? AND especialidad_id = ?";
        return jdbcTemplate.queryForObject(sql, Double.class, medicoId, especialidadId);
    }

    private RowMapper<EvaluacionDTO> evaluacionRowMapper() {
        return (rs, rowNum) -> {
            EvaluacionDTO evaluacion = new EvaluacionDTO();
            evaluacion.setEvaluacionId(rs.getInt("evaluacion_id"));
            evaluacion.setConsultaId(rs.getInt("consulta_id"));
            evaluacion.setPacienteId(rs.getInt("paciente_id"));
            evaluacion.setMedicoId(rs.getInt("medico_id"));
            evaluacion.setEspecialidadId(rs.getInt("especialidad_id"));
            evaluacion.setPuntuacion(rs.getInt("puntuacion"));
            evaluacion.setComentario(rs.getString("comentario"));
            evaluacion.setFechaEvaluacion(rs.getTimestamp("fecha_evaluacion").toLocalDateTime());
            return evaluacion;
        };
    }
    public List<EvaluacionDTO> obtenerTodasLasEvaluaciones() {
        String sql = """
            SELECT 
                e.evaluacion_id,
                e.consulta_id,
                e.paciente_id,
                e.medico_id,
                e.especialidad_id,
                e.puntuacion,
                e.comentario,
                e.fecha_evaluacion,
                u_p.nombre AS nombre_paciente,
                u_p.apellido AS apellido_paciente,
                u_m.nombre AS nombre_medico,
                u_m.apellido AS apellido_medico,
                esp.nombre AS nombre_especialidad
            FROM 
                Evaluaciones e
            JOIN Pacientes p ON e.paciente_id = p.paciente_id
            JOIN Usuarios u_p ON p.usuario_id = u_p.usuario_id
            JOIN Medicos m ON e.medico_id = m.medico_id
            JOIN Usuarios u_m ON m.usuario_id = u_m.usuario_id
            JOIN Especialidades esp ON e.especialidad_id = esp.especialidad_id
            ORDER BY e.fecha_evaluacion DESC
        """;
    
        return jdbcTemplate.query(sql, evaluacionRowMapperConDetalles());
    }
    
    private RowMapper<EvaluacionDTO> evaluacionRowMapperConDetalles() {
        return (rs, rowNum) -> {
            EvaluacionDTO evaluacion = new EvaluacionDTO();
            evaluacion.setEvaluacionId(rs.getInt("evaluacion_id"));
            evaluacion.setConsultaId(rs.getInt("consulta_id"));
            evaluacion.setPacienteId(rs.getInt("paciente_id"));
            evaluacion.setMedicoId(rs.getInt("medico_id"));
            evaluacion.setEspecialidadId(rs.getInt("especialidad_id"));
            evaluacion.setPuntuacion(rs.getInt("puntuacion"));
            evaluacion.setComentario(rs.getString("comentario"));
            evaluacion.setFechaEvaluacion(rs.getTimestamp("fecha_evaluacion").toLocalDateTime());
            evaluacion.setNombrePaciente(rs.getString("nombre_paciente"));
            evaluacion.setApellidoPaciente(rs.getString("apellido_paciente"));
            evaluacion.setNombreMedico(rs.getString("nombre_medico"));
            evaluacion.setApellidoMedico(rs.getString("apellido_medico"));
            evaluacion.setNombreEspecialidad(rs.getString("nombre_especialidad"));
            return evaluacion;
        };
    }
    
    
}
