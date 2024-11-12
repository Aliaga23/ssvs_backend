package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.model.User;
import com.ssvs.SSVS.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Obtener todos los usuarios
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo usuario
    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    // Actualizar un usuario
    @PutMapping("/{id}")
    public void updateUser(@PathVariable int id, @RequestBody User user) {
        userService.updateUser(id, user);
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    // Cambiar contraseña de usuario
    @PostMapping("/{id}/change-password")
    public ResponseEntity<String> changePassword(
            @PathVariable int id,
            @RequestParam String currentPassword,
            @RequestParam String newPassword) {
        boolean success = userService.changePassword(id, currentPassword, newPassword);
        if (success) {
            return ResponseEntity.ok("Contraseña cambiada exitosamente");
        } else {
            return ResponseEntity.badRequest().body("Contraseña actual incorrecta");
        }
    }

    // Obtener usuarios con rol de Paciente
    @GetMapping("/pacientes")
    public List<User> getUsersWithPacienteRole() {
        return userService.getUsersWithPacienteRole();
    }

    // Obtener usuarios con rol de Medico
    @GetMapping("/medicos")
    public List<User> getUsersWithMedicoRole() {
        return userService.getUsersWithMedicoRole();
    }
}
