package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody User user) {

        try {
            User createdUser = userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.OK).body("Sucesso ao registrar usuário! ");
        } catch (Exception e) {
            // Loga a exceção para depuração
            e.printStackTrace(); // Ou use um logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao registrar usuário: " + e.getMessage());
        }
    }}
