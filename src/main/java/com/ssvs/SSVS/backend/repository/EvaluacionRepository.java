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
}
