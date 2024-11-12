package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.model.User;
import com.ssvs.SSVS.backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    public Integer getPacienteIdByUserId(int userId) {
        return userRepository.findPacienteIdByUserId(userId);
    }
    
    public Integer getMedicoIdByUserId(int userId) {
        return userRepository.findMedicoIdByUserId(userId);
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
 // Método para construir los claims del usuario para el token
    public Map<String, Object> getUserClaims(User user) {
        Map<String, Object> claims = Map.of(
            "id", user.getId(),
            "nombre", user.getNombre(),
            "apellido", user.getApellido(),
            "email", user.getEmail(),
            "rolId", user.getRolId()
        );

        // Añadir `pacienteId` o `medicoId` dependiendo del rol
        if (user.getRolId() == 2) { // Ejemplo de rol 2 como Paciente
            int pacienteId = userRepository.findPacienteIdByUserId(user.getId());
            claims.put("pacienteId", pacienteId);
        } else if (user.getRolId() == 3) { // Ejemplo de rol 3 como Médico
            int medicoId = userRepository.findMedicoIdByUserId(user.getId());
            claims.put("medicoId", medicoId);
        }

        return claims;
    }
    public void createUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    public void updateUser(int id, User user) {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);
        } else {
            User currentUser = userRepository.findById(id);
            user.setPassword(currentUser.getPassword());
        }
        userRepository.update(id, user);
    }

    public void deleteUser(int id) {
        userRepository.delete(id);
    }

    // Method to verify the raw password with the hashed password in the database
    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    // Change password using BCrypt
    public boolean changePassword(int userId, String currentPassword, String newPassword) {
        User user = userRepository.findById(userId);
        if (user != null && verifyPassword(currentPassword, user.getPassword())) {
            String hashedNewPassword = passwordEncoder.encode(newPassword);
            userRepository.changePassword(userId, hashedNewPassword);
            return true;
        }
        return false;
    }

    public List<User> getUsersWithPacienteRole() {
        return userRepository.findUsersWithPacienteRole();
    }

    public List<User> getUsersWithMedicoRole() {
        return userRepository.findUsersWithMedicoRole();
    }
}
