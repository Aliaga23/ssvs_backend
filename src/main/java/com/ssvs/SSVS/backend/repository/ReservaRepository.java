// src/main/java/com/ssvs/backend/repository/ReservaRepository.java

package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.dto.ReservaInfoDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public class ReservaRepository {
    private final JdbcTemplate jdbcTemplate;

    public ReservaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ReservaInfoDTO> findReservasWithDetails() {
        String sql = """
            SELECT 
                r.reserva_id,
                p.paciente_id,
                u.nombre AS nombre_paciente,
                u.apellido AS apellido_paciente,
                r.fecha_reserva,
                r.estado,
                c.id AS cupo_id,
                c.hora_inicio,
                c.hora_final,
                m.medico_id,
                u_m.nombre AS nombre_medico,
                u_m.apellido AS apellido_medico,
                e.especialidad_id,
                e.nombre AS nombre_especialidad,
                h.historia_id  -- Incluye historia_id en la consulta
            FROM Reservas r
            JOIN Pacientes p ON r.paciente_id = p.paciente_id
            JOIN Usuarios u ON p.usuario_id = u.usuario_id
            JOIN CUPO c ON r.cupo_id = c.id
            JOIN Horario_Medico_Especialidad hme ON c.horario_medico_especialidad_id = hme.horario_medico_especialidad_id
            JOIN Medicos m ON hme.medico_id = m.medico_id
            JOIN Usuarios u_m ON m.usuario_id = u_m.usuario_id
            JOIN Especialidades e ON hme.especialidad_id = e.especialidad_id
            LEFT JOIN Historias_Clinicas h ON h.paciente_id = p.paciente_id -- Une con Historias_Clinicas
        """;
    
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ReservaInfoDTO reserva = new ReservaInfoDTO();
            reserva.setReservaId(rs.getInt("reserva_id"));
            reserva.setPacienteId(rs.getInt("paciente_id"));
            reserva.setNombrePaciente(rs.getString("nombre_paciente"));
            reserva.setApellidoPaciente(rs.getString("apellido_paciente"));
            reserva.setFechaReserva(rs.getDate("fecha_reserva").toLocalDate());
            reserva.setEstado(rs.getString("estado"));
            reserva.setCupoId(rs.getInt("cupo_id"));
            reserva.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
            reserva.setHoraFin(rs.getTime("hora_final").toLocalTime());
            reserva.setMedicoId(rs.getInt("medico_id"));
            reserva.setNombreMedico(rs.getString("nombre_medico"));
            reserva.setApellidoMedico(rs.getString("apellido_medico"));
            reserva.setEspecialidadId(rs.getInt("especialidad_id"));
            reserva.setNombreEspecialidad(rs.getString("nombre_especialidad"));
            reserva.setHistoriaId(rs.getInt("historia_id")); // Asigna historia_id a ReservaInfoDTO
            return reserva;
        });
    }
    
    public ReservaInfoDTO findReservaById(int reservaId) {
        String sql = """
            SELECT 
                r.reserva_id,
                p.paciente_id,
                u.nombre AS nombre_paciente,
                u.apellido AS apellido_paciente,
                r.fecha_reserva,
                r.estado,
                c.id AS cupo_id,
                c.hora_inicio,
                c.hora_final,
                m.medico_id,
                u_m.nombre AS nombre_medico,
                u_m.apellido AS apellido_medico,
                e.especialidad_id,
                e.nombre AS nombre_especialidad,
                h.historia_id  -- Incluye historia_id en la consulta
            FROM Reservas r
            JOIN Pacientes p ON r.paciente_id = p.paciente_id
            JOIN Usuarios u ON p.usuario_id = u.usuario_id
            JOIN CUPO c ON r.cupo_id = c.id
            JOIN Horario_Medico_Especialidad hme ON c.horario_medico_especialidad_id = hme.horario_medico_especialidad_id
            JOIN Medicos m ON hme.medico_id = m.medico_id
            JOIN Usuarios u_m ON m.usuario_id = u_m.usuario_id
            JOIN Especialidades e ON hme.especialidad_id = e.especialidad_id
            LEFT JOIN Historias_Clinicas h ON h.paciente_id = p.paciente_id -- Une con Historias_Clinicas
            WHERE r.reserva_id = ?
        """;
    
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            ReservaInfoDTO reserva = new ReservaInfoDTO();
            reserva.setReservaId(rs.getInt("reserva_id"));
            reserva.setPacienteId(rs.getInt("paciente_id"));
            reserva.setNombrePaciente(rs.getString("nombre_paciente"));
            reserva.setApellidoPaciente(rs.getString("apellido_paciente"));
            reserva.setFechaReserva(rs.getDate("fecha_reserva").toLocalDate());
            reserva.setEstado(rs.getString("estado"));
            reserva.setCupoId(rs.getInt("cupo_id"));
            reserva.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
            reserva.setHoraFin(rs.getTime("hora_final").toLocalTime());
            reserva.setMedicoId(rs.getInt("medico_id"));
            reserva.setNombreMedico(rs.getString("nombre_medico"));
            reserva.setApellidoMedico(rs.getString("apellido_medico"));
            reserva.setEspecialidadId(rs.getInt("especialidad_id"));
            reserva.setNombreEspecialidad(rs.getString("nombre_especialidad"));
            reserva.setHistoriaId(rs.getInt("historia_id")); // Asigna historia_id a ReservaInfoDTO
            return reserva;
        }, reservaId);
    }
    
    public int saveReserva(int pacienteId, int cupoId, LocalDate fechaReserva, String estado) {
        String sql = "INSERT INTO Reservas (paciente_id, cupo_id, fecha_reserva, estado) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, pacienteId, cupoId, fechaReserva, estado);
    }

    public int updateReserva(int reservaId, int pacienteId, int cupoId, LocalDate fechaReserva, String estado) {
        String sql = "UPDATE Reservas SET paciente_id = ?, cupo_id = ?, fecha_reserva = ?, estado = ? WHERE reserva_id = ?";
        return jdbcTemplate.update(sql, pacienteId, cupoId, fechaReserva, estado, reservaId);
    }
    public List<ReservaInfoDTO> findReservasByMedicoId(int medicoId) {
        String sql = """
            SELECT 
                r.reserva_id,
                p.paciente_id,
                u.nombre AS nombre_paciente,
                u.apellido AS apellido_paciente,
                r.fecha_reserva,
                r.estado,
                c.id AS cupo_id,
                c.hora_inicio,
                c.hora_final,
                m.medico_id,
                u_m.nombre AS nombre_medico,
                u_m.apellido AS apellido_medico,
                e.especialidad_id,
                e.nombre AS nombre_especialidad,
                h.historia_id  -- Incluye historia_id en la consulta
            FROM Reservas r
            JOIN Pacientes p ON r.paciente_id = p.paciente_id
            JOIN Usuarios u ON p.usuario_id = u.usuario_id
            JOIN CUPO c ON r.cupo_id = c.id
            JOIN Horario_Medico_Especialidad hme ON c.horario_medico_especialidad_id = hme.horario_medico_especialidad_id
            JOIN Medicos m ON hme.medico_id = m.medico_id
            JOIN Usuarios u_m ON m.usuario_id = u_m.usuario_id
            JOIN Especialidades e ON hme.especialidad_id = e.especialidad_id
            LEFT JOIN Historias_Clinicas h ON h.paciente_id = p.paciente_id
            WHERE m.medico_id = ?
        """;
    
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ReservaInfoDTO reserva = new ReservaInfoDTO();
            reserva.setReservaId(rs.getInt("reserva_id"));
            reserva.setPacienteId(rs.getInt("paciente_id"));
            reserva.setNombrePaciente(rs.getString("nombre_paciente"));
            reserva.setApellidoPaciente(rs.getString("apellido_paciente"));
            reserva.setFechaReserva(rs.getDate("fecha_reserva").toLocalDate());
            reserva.setEstado(rs.getString("estado"));
            reserva.setCupoId(rs.getInt("cupo_id"));
            reserva.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
            reserva.setHoraFin(rs.getTime("hora_final").toLocalTime());
            reserva.setMedicoId(rs.getInt("medico_id"));
            reserva.setNombreMedico(rs.getString("nombre_medico"));
            reserva.setApellidoMedico(rs.getString("apellido_medico"));
            reserva.setEspecialidadId(rs.getInt("especialidad_id"));
            reserva.setNombreEspecialidad(rs.getString("nombre_especialidad"));
            reserva.setHistoriaId(rs.getInt("historia_id"));
            return reserva;
        }, medicoId);
    }
    public int deleteReserva(int reservaId) {
        String sql = "DELETE FROM Reservas WHERE reserva_id = ?";
        return jdbcTemplate.update(sql, reservaId);
    }
}
