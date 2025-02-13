package com.example.demo.controller;

import com.example.demo.controller.dto.EmailUserDTO;
import com.example.demo.controller.dto.RecoveryDTO;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }
    @PostMapping
    public ResponseEntity<Void> postUser(@Valid @RequestBody UserDTO userDTO) {

        userService.crearUsuario(userDTO);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/activation")
    public ResponseEntity<Void> activateUser(@RequestBody String key) {
        userService.activateUsuario(key);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/password")
    public ResponseEntity<Void> requestRecovery(@Valid @RequestBody EmailUserDTO emailUserDTO) {
        userService.requestPasswordReset(emailUserDTO);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("/password")
    public ResponseEntity<Void> updatePassword(@Valid @RequestBody RecoveryDTO recoveryDTO) {
        userService.resetPassword(recoveryDTO);
        return ResponseEntity.noContent().build();
    }
}
