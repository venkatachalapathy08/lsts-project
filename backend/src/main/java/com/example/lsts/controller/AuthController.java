package com.example.lsts.controller;

import com.example.lsts.dto.logindto.LoginRequestDTO;
import com.example.lsts.dto.logindto.LoginResponseDTO;
import com.example.lsts.entity.UserEntity;
import com.example.lsts.repository.UserRepository;
import com.example.lsts.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {

        UserEntity user = userRepository.findByEmail(request.getEmail().trim())
                .orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        // 🔥 IMPORTANT: send role also
        return ResponseEntity.ok(
                new LoginResponseDTO(token, user.getRole().name() , user.getName())
        );
    }
}