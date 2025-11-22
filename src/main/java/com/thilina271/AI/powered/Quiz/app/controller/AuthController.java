package com.thilina271.AI.powered.Quiz.app.controller;

import com.thilina271.AI.powered.Quiz.app.dto.AuthRequestDTO;
import com.thilina271.AI.powered.Quiz.app.dto.UserDTO;
import com.thilina271.AI.powered.Quiz.app.service.AuthService;
import com.thilina271.AI.powered.Quiz.app.validation.LoginGroup;
import com.thilina271.AI.powered.Quiz.app.validation.RegisterGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<UserDTO> register(@Validated(RegisterGroup.class) @RequestBody AuthRequestDTO authRequest) {
        return authService.register(authRequest);
    }

    @PostMapping("login")
    public ResponseEntity<UserDTO> login(@Validated(LoginGroup.class) @RequestBody AuthRequestDTO authRequest) {
       return authService.login(authRequest);
    }

    @PostMapping("logout")
    public ResponseEntity<Void> logout(){
        return authService.logout();
    }

    @GetMapping("me")
    public ResponseEntity<UserDTO> getMe(){
        return authService.getMe();
    }
}
