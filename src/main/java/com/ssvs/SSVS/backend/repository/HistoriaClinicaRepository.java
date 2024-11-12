package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.model.HistoriaClinica;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.ssvs.SSVS.backend.model.HistoriaClinicaCompletaDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class HistoriaClinicaRepository {

    private final JdbcTemplate jdbcTemplate;

    public HistoriaClinicaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<HistoriaClinica> rowMapper = new RowMapper<HistoriaClinica>() {
        @Override
        public HistoriaClinica mapRow(ResultSet rs, int rowNum) throws SQLException {
            HistoriaClinica historiaClinica = new HistoriaClinica();
            historiaClinica.setId(rs.getInt("historia_id"));
            historiaClinica.setPacienteId(rs.getInt("paciente_id"));
            historiaClinica.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
            return historiaClinica;
        }
    };

    public List<HistoriaClinica> findAll() {
        String sql = "SELECT * FROM Historias_Clinicas";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public HistoriaClinica findById(int id) {
        String sql = "SELECT * FROM Historias_Clinicas WHERE historia_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void save(HistoriaClinica historiaClinica) {
        String sql = "INSERT INTO Historias_Clinicas (paciente_id, fecha_creacion) VALUES (?, ?)";
        jdbcTemplate.update(sql, historiaClinica.getPacienteId(), historiaClinica.getFechaCreacion());
    }

    public void update(int id, HistoriaClinica historiaClinica) {
        String sql = "UPDATE Historias_Clinicas SET paciente_id = ?, fecha_creacion = ? WHERE historia_id = ?";
        jdbcTemplate.update(sql, historiaClinica.getPacienteId(), historiaClinica.getFechaCreacion(), id);
    }
    public List<HistoriaClinicaCompletaDTO> findHistoriaClinicaResumenByPacienteId(int pacienteId) {
        String sql = "SELECT " +
                     "p.paciente_id, " +
                     "u.nombre AS nombre_paciente, " +
                     "u.apellido AS apellido_paciente, " +
                     "h.historia_id, " +
                     "h.fecha_creacion AS fecha_creacion_historia, " +
                     "cm.consulta_id, " +
                     "cm.fecha_consulta, " +
                     "cm.diagnostico, " +
                     "cm.tratamiento, " +
                     "cm.notas AS notas_consulta, " +
                     "COALESCE(med.medicamentos_receta, 'No tiene medicamentos') AS medicamentos_receta, " +
                     "COALESCE(lab.laboratorios, 'No tiene laboratorios') AS laboratorios " +
                     "FROM Pacientes p " +
                     "JOIN Usuarios u ON p.usuario_id = u.usuario_id " +
                     "JOIN Historias_Clinicas h ON p.paciente_id = h.paciente_id " +
                     "LEFT JOIN Consultas_Medicas cm ON h.historia_id = cm.historia_id " +
                     "LEFT JOIN LATERAL ( " +
                     "    SELECT STRING_AGG(m.nombre || ' (Dosis: ' || rm.dosis || ', Frecuencia: ' || rm.frecuencia || ', Duración: ' || rm.duracion || ' días)', '; ') AS medicamentos_receta " +
                     "    FROM Recetas r " +
                     "    LEFT JOIN Recetas_Medicamentos rm ON r.receta_id = rm.receta_id " +
                     "    LEFT JOIN Medicamentos m ON rm.medicamento_id = m.medicamento_id " +
                     "    WHERE r.consulta_id = cm.consulta_id " +
                     "    GROUP BY r.consulta_id " +
                     ") AS med ON true " +
                     "LEFT JOIN LATERAL ( " +
                     "    SELECT STRING_AGG(l.tipo_analisis || ' - ' || l.descripcion || ' (Programado: ' || l.fecha_programacion || ')', '; ') AS laboratorios " +
                     "    FROM Laboratorio l " +
                     "    WHERE l.consulta_id = cm.consulta_id " +
                     ") AS lab ON true " +
                     "WHERE p.paciente_id = ? " +
                     "ORDER BY cm.fecha_consulta DESC";

                     return jdbcTemplate.query(sql, new Object[]{pacienteId}, new RowMapper<HistoriaClinicaCompletaDTO>() {
                        @Override
                        public HistoriaClinicaCompletaDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                            HistoriaClinicaCompletaDTO dto = new HistoriaClinicaCompletaDTO();
                            dto.setPacienteId(rs.getInt("paciente_id"));
                            dto.setNombrePaciente(rs.getString("nombre_paciente"));
                            dto.setApellidoPaciente(rs.getString("apellido_paciente"));
                            dto.setHistoriaId(rs.getInt("historia_id"));
                            
                            // Maneja posibles valores nulos en `fecha_creacion_historia`
                            dto.setFechaCreacionHistoria(rs.getTimestamp("fecha_creacion_historia") != null ? 
                                rs.getTimestamp("fecha_creacion_historia").toLocalDateTime() : null);
                            
                            dto.setConsultaId(rs.getInt("consulta_id"));
                            
                            // Maneja posibles valores nulos en `fecha_consulta`
                            dto.setFechaConsulta(rs.getTimestamp("fecha_consulta") != null ? 
                                rs.getTimestamp("fecha_consulta").toLocalDateTime() : null);
                            
                            dto.setDiagnostico(rs.getString("diagnostico"));
                            dto.setTratamiento(rs.getString("tratamiento"));
                            dto.setNotasConsulta(rs.getString("notas_consulta"));
                            dto.setMedicamentosReceta(rs.getString("medicamentos_receta"));
                            dto.setLaboratorios(rs.getString("laboratorios"));
                            
                            return dto;
                        }
                    });
                }                    
    public void delete(int id) {
        String sql = "DELETE FROM Historias_Clinicas WHERE historia_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
