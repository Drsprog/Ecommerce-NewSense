package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.UpdateUserRequest;
import com.ecommerce.backend.dto.UpdateUserRoleRequest;
import com.ecommerce.backend.dto.UserResponse;
import com.ecommerce.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 🔹 Actualizar perfil del usuario
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable String id,
            @RequestBody UpdateUserRequest request) {
        UserResponse updated = userService.updateUser(id, request);
        return ResponseEntity.ok(updated);
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

