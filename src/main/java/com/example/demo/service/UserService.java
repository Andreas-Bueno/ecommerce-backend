package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    // Inicializando o BCryptPasswordEncoder no construtor
    public UserService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registerUser(User userDTO) {

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email já está em uso: " + userDTO.getEmail());
        }

        // Converte UserDTO para User
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        // Aqui você deve garantir que a senha do UserDTO está sendo usada
        String hashedPassword = passwordEncoder.encode(userDTO.getPwd()); // Modificado para usar userDTO

        user.setPwd(hashedPassword); // Define a senha hasheada no objeto user
        user.setPhone(userDTO.getPhone());

        return userRepository.save(user);
    }
}
