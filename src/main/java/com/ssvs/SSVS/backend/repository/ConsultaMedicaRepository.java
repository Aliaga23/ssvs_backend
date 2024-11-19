// src/main/java/com/ssvs/backend/repository/ConsultaMedicaRepository.java

package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.dto.ConsultaMedicaDTO;
import com.ssvs.SSVS.backend.model.ConsultaMedica;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConsultaMedicaRepository {
    private final JdbcTemplate jdbcTemplate;

    public ConsultaMedicaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private RowMapper<ConsultaMedicaDTO> consultaInfoRowMapper = (rs, rowNum) -> {
        ConsultaMedicaDTO info = new ConsultaMedicaDTO();
        info.setConsultaId(rs.getInt("consulta_id"));
        info.setReservaId(rs.getInt("reserva_id"));
        info.setHistoriaId(rs.getInt("historia_id"));
        info.setPacienteId(rs.getInt("paciente_id"));
        info.setMedicoId(rs.getInt("medico_id"));
        info.setCupoId(rs.getInt("cupo_id"));
        info.setEspecialidadId(rs.getInt("especialidad_id"));
        info.setFechaConsulta(rs.getTimestamp("fecha_consulta").toLocalDateTime());
        info.setDiagnostico(rs.getString("diagnostico"));
        info.setTratamiento(rs.getString("tratamiento"));
        info.setNotas(rs.getString("notas"));
        info.setNombrePaciente(rs.getString("nombre_paciente"));
        info.setApellidoPaciente(rs.getString("apellido_paciente"));
        info.setNombreMedico(rs.getString("nombre_medico"));
        info.setApellidoMedico(rs.getString("apellido_medico"));
        return info;
    };
    

    public List<ConsultaMedicaDTO> findAllWithDetails() {
        String sql = """
            SELECT c.consulta_id, c.reserva_id, c.historia_id, c.fecha_consulta, c.diagnostico, c.tratamiento, c.notas,
                   p.paciente_id, m.medico_id, cp.id AS cupo_id, e.especialidad_id,
                   u.nombre AS nombre_paciente, u.apellido AS apellido_paciente,
                   u_m.nombre AS nombre_medico, u_m.apellido AS apellido_medico
            FROM Consultas_Medicas c
            JOIN Reservas r ON c.reserva_id = r.reserva_id
            JOIN Pacientes p ON r.paciente_id = p.paciente_id
            JOIN Usuarios u ON p.usuario_id = u.usuario_id
            JOIN CUPO cp ON r.cupo_id = cp.id
            JOIN Horario_Medico_Especialidad hme ON cp.horario_medico_especialidad_id = hme.horario_medico_especialidad_id
            JOIN Medicos m ON hme.medico_id = m.medico_id
            JOIN Usuarios u_m ON m.usuario_id = u_m.usuario_id
            JOIN Especialidades e ON hme.especialidad_id = e.especialidad_id
        """;
        return jdbcTemplate.query(sql, consultaInfoRowMapper);
    }
    
    
    public ConsultaMedicaDTO findConsultaById(int consultaId) {
        String sql = """
            SELECT c.consulta_id, c.reserva_id, c.historia_id, c.fecha_consulta, c.diagnostico, c.tratamiento, c.notas,
                   p.paciente_id, m.medico_id, cp.id AS cupo_id, e.especialidad_id,
                   u.nombre AS nombre_paciente, u.apellido AS apellido_paciente,
                   um.nombre AS nombre_medico, um.apellido AS apellido_medico
            FROM Consultas_Medicas c
            JOIN Reservas r ON c.reserva_id = r.reserva_id
            JOIN Pacientes p ON r.paciente_id = p.paciente_id
            JOIN Usuarios u ON p.usuario_id = u.usuario_id
            JOIN CUPO cu ON r.cupo_id = cu.id
            JOIN Horario_Medico_Especialidad hme ON cu.horario_medico_especialidad_id = hme.horario_medico_especialidad_id
            JOIN Medicos m ON hme.medico_id = m.medico_id
            JOIN Usuarios um ON m.usuario_id = um.usuario_id
            JOIN Especialidades e ON hme.especialidad_id = e.especialidad_id
            WHERE c.consulta_id = ?
        """;
        return jdbcTemplate.queryForObject(sql, consultaInfoRowMapper, consultaId);
    }
    
    public int save(ConsultaMedica consulta) {
        String sql = "INSERT INTO Consultas_Medicas (reserva_id, historia_id, fecha_consulta, diagnostico, tratamiento, notas) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, consulta.getReservaId(), consulta.getHistoriaId(), consulta.getFechaConsulta(), consulta.getDiagnostico(), consulta.getTratamiento(), consulta.getNotas());
    }

    public int update(ConsultaMedica consulta) {
        String sql = "UPDATE Consultas_Medicas SET diagnostico = ?, tratamiento = ?, notas = ? WHERE consulta_id = ?";
        return jdbcTemplate.update(sql, consulta.getDiagnostico(), consulta.getTratamiento(), consulta.getNotas(), consulta.getConsultaId());
    }

    public int delete(int consultaId) {
        String sql = "DELETE FROM Consultas_Medicas WHERE consulta_id = ?";
        return jdbcTemplate.update(sql, consultaId);
    }
}
