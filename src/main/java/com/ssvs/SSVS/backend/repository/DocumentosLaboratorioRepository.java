package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.model.DocumentosLaboratorio;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DocumentosLaboratorioRepository {

    private final JdbcTemplate jdbcTemplate;

    public DocumentosLaboratorioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<DocumentosLaboratorio> rowMapper = new RowMapper<DocumentosLaboratorio>() {
        @Override
        public DocumentosLaboratorio mapRow(ResultSet rs, int rowNum) throws SQLException {
            DocumentosLaboratorio documento = new DocumentosLaboratorio();
            documento.setId(rs.getInt("documento_id"));
            documento.setLaboratorioId(rs.getInt("laboratorio_id"));
            documento.setNombreArchivo(rs.getString("nombre_archivo"));
            documento.setTipoDocumento(rs.getString("tipo_documento"));
            documento.setUbicacionArchivo(rs.getString("ubicacion_archivo"));
            documento.setFechaRealizacion(rs.getTimestamp("fecha_realizacion").toLocalDateTime());
            return documento;
        }
    };

    public List<DocumentosLaboratorio> findAll() {
        String sql = "SELECT * FROM Documentos_Laboratorio";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public DocumentosLaboratorio findById(int id) {
        String sql = "SELECT * FROM Documentos_Laboratorio WHERE documento_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void save(DocumentosLaboratorio documento) {
        String sql = "INSERT INTO Documentos_Laboratorio (laboratorio_id, nombre_archivo, tipo_documento, ubicacion_archivo, fecha_realizacion) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, documento.getLaboratorioId(), documento.getNombreArchivo(), documento.getTipoDocumento(), documento.getUbicacionArchivo(), documento.getFechaRealizacion());
    }

    public void update(int id, DocumentosLaboratorio documento) {
        String sql = "UPDATE Documentos_Laboratorio SET laboratorio_id = ?, nombre_archivo = ?, tipo_documento = ?, ubicacion_archivo = ?, fecha_realizacion = ? WHERE documento_id = ?";
        jdbcTemplate.update(sql, documento.getLaboratorioId(), documento.getNombreArchivo(), documento.getTipoDocumento(), documento.getUbicacionArchivo(), documento.getFechaRealizacion(), id);
    }

    public void delete(int id) {
        String sql = "DELETE FROM Documentos_Laboratorio WHERE documento_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
