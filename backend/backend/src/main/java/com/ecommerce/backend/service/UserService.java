package com.ecommerce.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.dto.UpdateUserRequest;
import com.ecommerce.backend.dto.UpdateUserRoleRequest;
import com.ecommerce.backend.dto.UserResponse;
import com.ecommerce.backend.model.User;
import com.ecommerce.backend.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public UserResponse updateUser(String id, UpdateUserRequest request){
        User user= userRepository.findById(id).
        orElseThrow(()->new RuntimeException("Usuario no encontrado"));

        if(request.getUsername()!= null){
            user.setUsername(request.getUsername());
        }

           if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        User updateUser= userRepository.save(user);

        return UserResponse.builder()
        .id(updateUser.getId())
        .username(updateUser.getUsername())
        .email(updateUser.getEmail())
        .role(updateUser.getRole())
        .build();
    }

     // actualizar solo el rol (admin)
    public UserResponse updateRole(String id, UpdateUserRoleRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setRole(request.getRole());
        User updatedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(updatedUser.getId())
                .username(updatedUser.getUsername())
                .email(updatedUser.getEmail())
                .role(updatedUser.getRole())
                .build();
    }
}
