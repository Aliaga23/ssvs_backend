package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.dto.RecetaDetallesDTO;
import com.ssvs.SSVS.backend.model.Receta;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


import java.util.List;

@Repository
public class RecetaRepository {

    private final JdbcTemplate jdbcTemplate;

    public RecetaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Receta> findAll() {
        String sql = "SELECT * FROM Recetas";
        return jdbcTemplate.query(sql, rowMapper);
    }

    private final RowMapper<Receta> rowMapper = (rs, rowNum) -> {
        Receta receta = new Receta();
        receta.setId(rs.getInt("receta_id"));
        receta.setConsultaId(rs.getInt("consulta_id"));
        receta.setFechaEmision(rs.getTimestamp("fecha_emision").toLocalDateTime());
        receta.setInstrucciones(rs.getString("instrucciones"));
        return receta;
    };

    @SuppressWarnings("deprecation")
    public Receta findById(int id) {
        String sql = "SELECT * FROM Recetas WHERE receta_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
    }
    @SuppressWarnings("deprecation")
    public List<RecetaDetallesDTO> getRecetaDetallesByConsultaId(int consultaId) {
        String sql = "SELECT r.fecha_emision, r.instrucciones, cm.fecha_consulta, cm.diagnostico, cm.tratamiento, cm.notas, "
                   + "p.nombre AS paciente_nombre, p.apellido AS paciente_apellido, m.nombre AS medico_nombre, "
                   + "m.apellido AS medico_apellido, e.nombre AS especialidad_nombre, d.dia_semana AS dia_atencion, "
                   + "cu.hora_inicio AS consulta_hora_inicio, cu.hora_final AS consulta_hora_final, "
                   + "medi.nombre AS medicamento_nombre, rm.dosis AS medicamento_dosis, rm.frecuencia AS medicamento_frecuencia, "
                   + "rm.duracion AS medicamento_duracion "
                   + "FROM Recetas r "
                   + "JOIN Consultas_Medicas cm ON r.consulta_id = cm.consulta_id "
                   + "JOIN Reservas res ON cm.reserva_id = res.reserva_id "
                   + "JOIN Pacientes pac ON res.paciente_id = pac.paciente_id "
                   + "JOIN Usuarios p ON pac.usuario_id = p.usuario_id "
                   + "JOIN CUPO cu ON res.cupo_id = cu.id "
                   + "JOIN Horario_Medico_Especialidad hme ON cu.horario_medico_especialidad_id = hme.horario_medico_especialidad_id "
                   + "JOIN Medicos med ON hme.medico_id = med.medico_id "
                   + "JOIN Usuarios m ON med.usuario_id = m.usuario_id "
                   + "JOIN Especialidades e ON hme.especialidad_id = e.especialidad_id "
                   + "JOIN HORARIOS h ON hme.horario_id = h.id "
                   + "JOIN DIA d ON h.dia_id = d.id "
                   + "LEFT JOIN Recetas_Medicamentos rm ON r.receta_id = rm.receta_id "
                   + "LEFT JOIN Medicamentos medi ON rm.medicamento_id = medi.medicamento_id "
                   + "WHERE r.consulta_id = ?";
    
        return jdbcTemplate.query(sql, new Object[]{consultaId}, (rs, rowNum) -> {
            RecetaDetallesDTO detalles = new RecetaDetallesDTO();
            detalles.setFechaEmision(rs.getTimestamp("fecha_emision").toLocalDateTime());
            detalles.setInstrucciones(rs.getString("instrucciones"));
            detalles.setFechaConsulta(rs.getTimestamp("fecha_consulta").toLocalDateTime());
            detalles.setDiagnostico(rs.getString("diagnostico"));
            detalles.setTratamiento(rs.getString("tratamiento"));
            detalles.setNotas(rs.getString("notas"));
            detalles.setPacienteNombre(rs.getString("paciente_nombre"));
            detalles.setPacienteApellido(rs.getString("paciente_apellido"));
            detalles.setMedicoNombre(rs.getString("medico_nombre"));
            detalles.setMedicoApellido(rs.getString("medico_apellido"));
            detalles.setEspecialidadNombre(rs.getString("especialidad_nombre"));
            detalles.setDiaAtencion(rs.getString("dia_atencion"));
            detalles.setConsultaHoraInicio(rs.getTime("consulta_hora_inicio").toLocalTime().toString());
            detalles.setConsultaHoraFinal(rs.getTime("consulta_hora_final").toLocalTime().toString());
            detalles.setMedicamentoNombre(rs.getString("medicamento_nombre"));
            detalles.setMedicamentoDosis(rs.getString("medicamento_dosis"));
            detalles.setMedicamentoFrecuencia(rs.getString("medicamento_frecuencia"));
            detalles.setMedicamentoDuracion(rs.getInt("medicamento_duracion"));
            return detalles;
        });
    }
    

    public int save(Receta receta) {
        String sql = "INSERT INTO Recetas (consulta_id, fecha_emision, instrucciones) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
    
        jdbcTemplate.update(connection -> {
            var ps = connection.prepareStatement(sql, new String[]{"receta_id"});
            ps.setInt(1, receta.getConsultaId());
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(receta.getFechaEmision()));
            ps.setString(3, receta.getInstrucciones());
            return ps;
        }, keyHolder);
    
        return keyHolder.getKey().intValue(); // Devuelve el id generado
    }
    

    public void update(int id, Receta receta) {
        String sql = "UPDATE Recetas SET consulta_id = ?, fecha_emision = ?, instrucciones = ? WHERE receta_id = ?";
        jdbcTemplate.update(sql, receta.getConsultaId(), receta.getFechaEmision(), receta.getInstrucciones(), id);
    }

    public void delete(int id) {
        String sql = "DELETE FROM Recetas WHERE receta_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
