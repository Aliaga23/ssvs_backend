package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.model.Laboratorio;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LaboratorioRepository {

    private final JdbcTemplate jdbcTemplate;

    public LaboratorioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Laboratorio> rowMapper = new RowMapper<Laboratorio>() {
        @Override
        public Laboratorio mapRow(ResultSet rs, int rowNum) throws SQLException {
            Laboratorio laboratorio = new Laboratorio();
            laboratorio.setId(rs.getInt("laboratorio_id"));
            laboratorio.setConsultaId(rs.getInt("consulta_id"));
            laboratorio.setTipoAnalisis(rs.getString("tipo_analisis"));
            laboratorio.setDescripcion(rs.getString("descripcion"));
            laboratorio.setFechaProgramacion(rs.getTimestamp("fecha_programacion").toLocalDateTime());
            return laboratorio;
        }
    };

    public List<Laboratorio> findAll() {
        String sql = "SELECT * FROM Laboratorio";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Laboratorio findById(int id) {
        String sql = "SELECT * FROM Laboratorio WHERE laboratorio_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void save(Laboratorio laboratorio) {
        String sql = "INSERT INTO Laboratorio (consulta_id, tipo_analisis, descripcion, fecha_programacion) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, laboratorio.getConsultaId(), laboratorio.getTipoAnalisis(), laboratorio.getDescripcion(), laboratorio.getFechaProgramacion());
    }

    public void update(int id, Laboratorio laboratorio) {
        String sql = "UPDATE Laboratorio SET consulta_id = ?, tipo_analisis = ?, descripcion = ?, fecha_programacion = ? WHERE laboratorio_id = ?";
        jdbcTemplate.update(sql, laboratorio.getConsultaId(), laboratorio.getTipoAnalisis(), laboratorio.getDescripcion(), laboratorio.getFechaProgramacion(), id);
    }
    public Laboratorio findByConsultaId(int consultaId) {
        String sql = "SELECT * FROM Laboratorio WHERE consulta_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, consultaId);
    }
    public void delete(int id) {
        String sql = "DELETE FROM Laboratorio WHERE laboratorio_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
