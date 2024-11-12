// src/main/java/com/ssvs/SSVS/backend/repository/UserRepository.java
package com.ssvs.SSVS.backend.repository;

import com.ssvs.SSVS.backend.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("usuario_id"));
        user.setNombre(rs.getString("nombre"));
        user.setApellido(rs.getString("apellido"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setTelefono(rs.getString("telefono"));
        user.setDireccion(rs.getString("direccion"));
        user.setRolId(rs.getInt("rol_id"));
        return user;
    };
    public User findByEmail(String email) {
        String sql = "SELECT * FROM Usuarios WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, email);
    }
    
    public List<User> findAll() {
        String sql = "SELECT * FROM Usuarios";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public User findById(int id) {
        String sql = "SELECT * FROM Usuarios WHERE usuario_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void save(User user) {
        String sql = "INSERT INTO Usuarios (nombre, apellido, email, password, telefono, direccion, rol_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getNombre(), user.getApellido(), user.getEmail(), user.getPassword(), user.getTelefono(), user.getDireccion(), user.getRolId());
    }

    public void update(int id, User user) {
        String sql = "UPDATE Usuarios SET nombre = ?, apellido = ?, email = ?, password = ?, telefono = ?, direccion = ?, rol_id = ? WHERE usuario_id = ?";
        jdbcTemplate.update(sql, user.getNombre(), user.getApellido(), user.getEmail(), user.getPassword(), user.getTelefono(), user.getDireccion(), user.getRolId(), id);
    }

    public void delete(int id) {
        String sql = "DELETE FROM Usuarios WHERE usuario_id = ?";
        jdbcTemplate.update(sql, id);
    }

    // Cambiar contraseña
    public void changePassword(int userId, String newPassword) {
        String sql = "UPDATE Usuarios SET password = ? WHERE usuario_id = ?";
        jdbcTemplate.update(sql, newPassword, userId);
    }

    // Obtener usuarios con rol de Paciente
    public List<User> findUsersWithPacienteRole() {
        String sql = "SELECT * FROM Usuarios WHERE rol_id = (SELECT rol_id FROM Roles WHERE nombre = 'Paciente')";
        return jdbcTemplate.query(sql, rowMapper);
    }
    public Integer findPacienteIdByUserId(int userId) {
        String sql = "SELECT paciente_id FROM Pacientes WHERE usuario_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, userId);
    }
    
    public Integer findMedicoIdByUserId(int userId) {
        String sql = "SELECT medico_id FROM Medicos WHERE usuario_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, userId);
    }
    
    // Obtener usuarios con rol de Medico
    public List<User> findUsersWithMedicoRole() {
        String sql = "SELECT * FROM Usuarios WHERE rol_id = (SELECT rol_id FROM Roles WHERE nombre = 'Medico')";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
