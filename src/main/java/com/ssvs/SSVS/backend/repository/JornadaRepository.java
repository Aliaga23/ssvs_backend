package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.model.Jornada;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JornadaRepository {

    private final JdbcTemplate jdbcTemplate;

    public JornadaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Jornada> rowMapper = new RowMapper<Jornada>() {
        @Override
        public Jornada mapRow(ResultSet rs, int rowNum) throws SQLException {
            Jornada jornada = new Jornada();
            jornada.setId(rs.getInt("id"));
            jornada.setNombre(rs.getString("nombre"));
            jornada.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
            jornada.setHoraFinal(rs.getTime("hora_final").toLocalTime());
            return jornada;
        }
    };

    public List<Jornada> findAll() {
        String sql = "SELECT * FROM JORNADA";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Jornada findById(int id) {
        String sql = "SELECT * FROM JORNADA WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public int save(Jornada jornada) {
        String sql = "INSERT INTO JORNADA (nombre, hora_inicio, hora_final) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, jornada.getNombre(), jornada.getHoraInicio(), jornada.getHoraFinal());
    }

    public int update(int id, Jornada jornada) {
        String sql = "UPDATE JORNADA SET nombre = ?, hora_inicio = ?, hora_final = ? WHERE id = ?";
        return jdbcTemplate.update(sql, jornada.getNombre(), jornada.getHoraInicio(), jornada.getHoraFinal(), id);
    }

    public int delete(int id) {
        String sql = "DELETE FROM JORNADA WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
