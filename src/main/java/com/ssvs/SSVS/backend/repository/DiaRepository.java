package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.model.Dia;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DiaRepository {

    private final JdbcTemplate jdbcTemplate;

    public DiaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Dia> rowMapper = new RowMapper<Dia>() {
        @Override
        public Dia mapRow(ResultSet rs, int rowNum) throws SQLException {
            Dia dia = new Dia();
            dia.setId(rs.getInt("id"));
            dia.setDiaSemana(rs.getString("dia_semana"));
            return dia;
        }
    };

    public List<Dia> findAll() {
        String sql = "SELECT * FROM DIA";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Dia findById(int id) {
        String sql = "SELECT * FROM DIA WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public int save(Dia dia) {
        String sql = "INSERT INTO DIA (dia_semana) VALUES (?)";
        return jdbcTemplate.update(sql, dia.getDiaSemana());
    }

    public int update(int id, Dia dia) {
        String sql = "UPDATE DIA SET dia_semana = ? WHERE id = ?";
        return jdbcTemplate.update(sql, dia.getDiaSemana(), id);
    }

    public int delete(int id) {
        String sql = "DELETE FROM DIA WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
