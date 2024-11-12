package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.model.Horario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

@Repository
public class HorarioRepository {

    private final JdbcTemplate jdbcTemplate;

    public HorarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Horario> rowMapper = new RowMapper<>() {
        @Override
        public Horario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Horario horario = new Horario();
            horario.setId(rs.getInt("id"));
            horario.setDiaId(rs.getInt("dia_id"));
            horario.setJornadaId(rs.getInt("jornada_id"));
            horario.setDiaSemana(rs.getString("dia_semana"));
            horario.setJornadaNombre(rs.getString("jornada_nombre"));
            horario.setHoraInicio(rs.getObject("hora_inicio", LocalTime.class));
            horario.setHoraFinal(rs.getObject("hora_final", LocalTime.class));
            return horario;
        }
    };

    public List<Horario> findAll() {
        String sql = """
            SELECT h.id, h.dia_id, h.jornada_id, d.dia_semana, j.nombre AS jornada_nombre, j.hora_inicio, j.hora_final
            FROM HORARIOS h
            JOIN DIA d ON h.dia_id = d.id
            JOIN JORNADA j ON h.jornada_id = j.id
        """;
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Horario findById(int id) {
        String sql = """
            SELECT h.id, h.dia_id, h.jornada_id, d.dia_semana, j.nombre AS jornada_nombre, j.hora_inicio, j.hora_final
            FROM HORARIOS h
            JOIN DIA d ON h.dia_id = d.id
            JOIN JORNADA j ON h.jornada_id = j.id
            WHERE h.id = ?
        """;
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void save(Horario horario) {
        String sql = "INSERT INTO HORARIOS (dia_id, jornada_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, horario.getDiaId(), horario.getJornadaId());
    }

    public void update(int id, Horario horario) {
        String sql = "UPDATE HORARIOS SET dia_id = ?, jornada_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, horario.getDiaId(), horario.getJornadaId(), id);
    }

    public void delete(int id) {
        String sql = "DELETE FROM HORARIOS WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
