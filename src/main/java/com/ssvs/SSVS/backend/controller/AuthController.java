package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.model.User;
import com.ssvs.SSVS.backend.service.UserService;
import com.ssvs.SSVS.backend.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(400).body("Email is already registered.");
        }

        user.setRolId(2); // Set a default role for registration, if needed
        userService.createUser(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("nombre", user.getNombre());
        claims.put("email", user.getEmail());
        claims.put("rolId", user.getRolId());

        // Check for patient or doctor role and add their specific IDs
        if (user.getRolId() == 2) { // Assume role ID 3 is for 'Paciente'
            Integer pacienteId = userService.getPacienteIdByUserId(user.getId());
            claims.put("pacienteId", pacienteId);
        } else if (user.getRolId() == 3) { // Assume role ID 4 is for 'Medico'
            Integer medicoId = userService.getMedicoIdByUserId(user.getId());
            claims.put("medicoId", medicoId);
        }

        String token = jwtUtil.generateTokenWithClaims(user.getEmail(), claims);

        return ResponseEntity.ok(Map.of(
            "token", token,
            "user", claims
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User userDb = userService.findByEmail(user.getEmail());

        if (userDb != null && userService.verifyPassword(user.getPassword(), userDb.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", userDb.getId());
            claims.put("nombre", userDb.getNombre());
            claims.put("email", userDb.getEmail());
            claims.put("rolId", userDb.getRolId());

            // Check for patient or doctor role and add their specific IDs
            if (userDb.getRolId() == 2) { // Assume role ID 3 is for 'Paciente'
                Integer pacienteId = userService.getPacienteIdByUserId(userDb.getId());
                claims.put("pacienteId", pacienteId);
            } else if (userDb.getRolId() == 3) { // Assume role ID 4 is for 'Medico'
                Integer medicoId = userService.getMedicoIdByUserId(userDb.getId());
                claims.put("medicoId", medicoId);
            }

            String token = jwtUtil.generateTokenWithClaims(user.getEmail(), claims);

            return ResponseEntity.ok(Map.of(
                "token", token,
                "user", claims
            ));
        }

        return ResponseEntity.status(401).body("Invalid credentials.");
    }
}
