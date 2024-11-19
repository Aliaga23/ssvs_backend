package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.model.MetodoDePago;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MetodoDePagoRepository {

    private final JdbcTemplate jdbcTemplate;

    public MetodoDePagoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Mapeo de los resultados SQL a un objeto MetodoDePago
    private RowMapper<MetodoDePago> rowMapper = new RowMapper<MetodoDePago>() {
        @Override
        public MetodoDePago mapRow(ResultSet rs, int rowNum) throws SQLException {
            MetodoDePago metodoDePago = new MetodoDePago();
            metodoDePago.setMetodoId(rs.getInt("metodo_id"));
            metodoDePago.setNombre(rs.getString("nombre"));
            metodoDePago.setDescripcion(rs.getString("descripcion"));
            return metodoDePago;
        }
    };

    // Obtener todos los métodos de pago
    public List<MetodoDePago> findAll() {
        String sql = "SELECT metodo_id, nombre, descripcion FROM MetodoDePago";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Buscar un método de pago por ID
    public MetodoDePago findById(int id) {
        String sql = "SELECT metodo_id, nombre, descripcion FROM MetodoDePago WHERE metodo_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    // Crear un nuevo método de pago
    public void save(MetodoDePago metodoDePago) {
        String sql = "INSERT INTO MetodoDePago (nombre, descripcion) VALUES (?, ?)";
        jdbcTemplate.update(sql, metodoDePago.getNombre(), metodoDePago.getDescripcion());
    }

    // Actualizar un método de pago
    public void update(int id, MetodoDePago metodoDePago) {
        String sql = "UPDATE MetodoDePago SET nombre = ?, descripcion = ? WHERE metodo_id = ?";
        jdbcTemplate.update(sql, metodoDePago.getNombre(), metodoDePago.getDescripcion(), id);
    }

    // Eliminar un método de pago
    public void delete(int id) {
        String sql = "DELETE FROM MetodoDePago WHERE metodo_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
