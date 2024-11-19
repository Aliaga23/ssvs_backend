package com.ssvs.SSVS.backend.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.ssvs.SSVS.backend.dto.PacienteConsultaDTO;

@Service
public class ReporteService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PacienteConsultaDTO> obtenerReportePacientes() {
        String sql = """
            SELECT 
                p.paciente_id,
                u_p.nombre AS nombre_paciente,
                u_p.apellido AS apellido_paciente,
                p.tipo_sangre AS tipo_sangre_paciente,
                p.fecha_nacimiento AS fecha_nacimiento_paciente,
                hclin.historia_id,
                hclin.fecha_creacion AS fecha_historia,
                cm.consulta_id,
                cm.fecha_consulta,
                cm.diagnostico,
                cm.tratamiento,
                cm.notas AS notas_consulta,
                COALESCE(
                    STRING_AGG(
                        med.nombre || ' (Dosis: ' || rm.dosis || ', Frecuencia: ' || rm.frecuencia || ', Duración: ' || rm.duracion || ' días)', 
                        '; '
                    ),
                    'No tiene medicamentos'
                ) AS medicamentos_recetados,
                COALESCE(
                    STRING_AGG(
                        lab.tipo_analisis || ': ' || lab.descripcion || ' (Programado: ' || lab.fecha_programacion || ')', 
                        '; '
                    ),
                    'No tiene análisis de laboratorio'
                ) AS laboratorios_asociados,
                m.medico_id,
                u_m.nombre AS nombre_medico,
                u_m.apellido AS apellido_medico,
                esp.nombre AS especialidad_medico,
                cupo.hora_inicio AS consulta_hora_inicio,
                cupo.hora_final AS consulta_hora_final,
                dia.dia_semana AS dia_consulta,
                jornada.nombre AS jornada_consulta
            FROM 
                Pacientes p
                JOIN Usuarios u_p ON p.usuario_id = u_p.usuario_id
                JOIN Historias_Clinicas hclin ON p.paciente_id = hclin.paciente_id
                LEFT JOIN Consultas_Medicas cm ON hclin.historia_id = cm.historia_id
                LEFT JOIN Reservas res ON cm.reserva_id = res.reserva_id
                LEFT JOIN CUPO cupo ON res.cupo_id = cupo.id
                LEFT JOIN Horario_Medico_Especialidad hme ON cupo.horario_medico_especialidad_id = hme.horario_medico_especialidad_id
                LEFT JOIN Medicos m ON hme.medico_id = m.medico_id
                LEFT JOIN Usuarios u_m ON m.usuario_id = u_m.usuario_id
                LEFT JOIN Especialidades esp ON hme.especialidad_id = esp.especialidad_id
                LEFT JOIN HORARIOS horarios ON hme.horario_id = horarios.id
                LEFT JOIN DIA dia ON horarios.dia_id = dia.id
                LEFT JOIN JORNADA jornada ON horarios.jornada_id = jornada.id
                LEFT JOIN Recetas receta ON cm.consulta_id = receta.consulta_id
                LEFT JOIN Recetas_Medicamentos rm ON receta.receta_id = rm.receta_id
                LEFT JOIN Medicamentos med ON rm.medicamento_id = med.medicamento_id
                LEFT JOIN Laboratorio lab ON cm.consulta_id = lab.consulta_id
            GROUP BY 
                p.paciente_id, u_p.nombre, u_p.apellido, p.tipo_sangre, p.fecha_nacimiento,
                hclin.historia_id, hclin.fecha_creacion,
                cm.consulta_id, cm.fecha_consulta, cm.diagnostico, cm.tratamiento, cm.notas,
                m.medico_id, u_m.nombre, u_m.apellido, esp.nombre,
                cupo.hora_inicio, cupo.hora_final, dia.dia_semana, jornada.nombre
            ORDER BY 
                cm.fecha_consulta DESC;
        """;

        return jdbcTemplate.query(sql, new PacienteConsultaRowMapper());
    }

    private static class PacienteConsultaRowMapper implements RowMapper<PacienteConsultaDTO> {
        @Override
        public PacienteConsultaDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            PacienteConsultaDTO dto = new PacienteConsultaDTO();
            dto.setPacienteId(rs.getInt("paciente_id"));
            dto.setNombrePaciente(rs.getString("nombre_paciente"));
            dto.setApellidoPaciente(rs.getString("apellido_paciente"));
            dto.setTipoSangrePaciente(rs.getString("tipo_sangre_paciente"));
            dto.setFechaNacimientoPaciente(rs.getString("fecha_nacimiento_paciente"));
            dto.setHistoriaId(rs.getInt("historia_id"));
            dto.setFechaHistoria(rs.getString("fecha_historia"));
            dto.setConsultaId(rs.getInt("consulta_id"));
            dto.setFechaConsulta(rs.getString("fecha_consulta"));
            dto.setDiagnostico(rs.getString("diagnostico"));
            dto.setTratamiento(rs.getString("tratamiento"));
            dto.setNotasConsulta(rs.getString("notas_consulta"));
            dto.setMedicamentosRecetados(rs.getString("medicamentos_recetados"));
            dto.setLaboratoriosAsociados(rs.getString("laboratorios_asociados"));
            dto.setMedicoId(rs.getInt("medico_id"));
            dto.setNombreMedico(rs.getString("nombre_medico"));
            dto.setApellidoMedico(rs.getString("apellido_medico"));
            dto.setEspecialidadMedico(rs.getString("especialidad_medico"));
            dto.setConsultaHoraInicio(rs.getString("consulta_hora_inicio"));
            dto.setConsultaHoraFinal(rs.getString("consulta_hora_final"));
            dto.setDiaConsulta(rs.getString("dia_consulta"));
            dto.setJornadaConsulta(rs.getString("jornada_consulta"));
            return dto;
        }
    }
}
