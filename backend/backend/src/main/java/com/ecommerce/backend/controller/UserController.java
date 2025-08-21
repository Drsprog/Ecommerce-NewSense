package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.UpdatePasswordRequest;
import com.ecommerce.backend.dto.UpdateUserRequest;
import com.ecommerce.backend.dto.UpdateUserRoleRequest;
import com.ecommerce.backend.dto.UserResponse;
import com.ecommerce.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Obtener perfil del usuario autenticado
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMyProfile(Authentication authentication) {
        String username = authentication.getName(); // viene del JWT
        UserResponse user = userService.getByUsername(username);
        return ResponseEntity.ok(user);
    }

    // Actualizar perfil del usuario autenticado
    @PutMapping("/me")
    public ResponseEntity<UserResponse> updateMyProfile(
            Authentication authentication,
            @RequestBody UpdateUserRequest request) {
        String username = authentication.getName(); // viene del JWT
        UserResponse updated = userService.updateByUsername(username, request);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/me/password")
    public ResponseEntity<?> updatePassword(
            Authentication authentication,
            @RequestBody UpdatePasswordRequest request) {
        String username = authentication.getName();
        userService.updatePassword(username, request);
        return ResponseEntity.ok("Contraseña actualizada");
    }

    // 🔹 Cambiar rol de un usuario (solo admin)
    @PutMapping("/{id}/role")
    public ResponseEntity<UserResponse> updateUserRole(
            @PathVariable String id,
            @RequestBody UpdateUserRoleRequest request) {
        UserResponse updated = userService.updateRole(id, request);
        return ResponseEntity.ok(updated);
    }

}
