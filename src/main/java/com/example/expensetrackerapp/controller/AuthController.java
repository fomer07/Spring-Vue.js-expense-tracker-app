package com.example.expensetrackerapp.controller;

import com.example.expensetrackerapp.dto.UserLoginRequestDto;
import com.example.expensetrackerapp.dto.UserRegistrationRequestDto;
import com.example.expensetrackerapp.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser (@Valid @RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
        try {
            String message = authService.registerUser(
                    userRegistrationRequestDto.getUsername(),
                    userRegistrationRequestDto.getEmail(),
                    userRegistrationRequestDto.getPassword()
            );
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto) {
        try {
            String token = authService.loginUser(
                    userLoginRequestDto.getUsername(),
                    userLoginRequestDto.getPassword()
            );
            return new ResponseEntity<>(token, HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
