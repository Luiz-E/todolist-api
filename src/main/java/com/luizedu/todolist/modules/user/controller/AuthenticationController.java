package com.luizedu.todolist.modules.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizedu.todolist.config.security.TokenService;
import com.luizedu.todolist.modules.user.service.UserService;
import com.luizedu.todolist.modules.user.dto.AuthenticationDTO;
import com.luizedu.todolist.modules.user.dto.LoginResponseDTO;
import com.luizedu.todolist.modules.user.model.UserModel;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@RequestBody @Valid AuthenticationDTO data) {
        try {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(data.username(), data.password());
            var auth = this.authenticationManager.authenticate(authentication);
            var token = tokenService.generateToken((UserModel) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register (@RequestBody @Valid AuthenticationDTO data) {
        try {
            if (this.userService.loadUserByUsername(data.username()) != null) {
                return ResponseEntity.badRequest().build();
            }
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
            UserModel user = new UserModel(data.username(), encryptedPassword);
            this.userService.save(user);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
