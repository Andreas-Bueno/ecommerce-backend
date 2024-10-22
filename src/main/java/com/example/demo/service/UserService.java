package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User registerUser(User userDTO) {

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email já está em uso: " + userDTO.getEmail());
        }

        // Converte UserDTO para User
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPwd(userDTO.getPwd());
        user.setPhone(userDTO.getPhone());

        return userRepository.save(user);
    }
}
