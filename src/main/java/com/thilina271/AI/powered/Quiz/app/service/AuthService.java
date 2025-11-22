package com.thilina271.AI.powered.Quiz.app.service;

import com.thilina271.AI.powered.Quiz.app.dto.AuthRequestDTO;
import com.thilina271.AI.powered.Quiz.app.dto.UserDTO;
import com.thilina271.AI.powered.Quiz.app.exception.BadRequestException;
import com.thilina271.AI.powered.Quiz.app.exception.ResourceNotFoundException;
import com.thilina271.AI.powered.Quiz.app.mapper.UserMapper;
import com.thilina271.AI.powered.Quiz.app.model.User;
import com.thilina271.AI.powered.Quiz.app.repository.UserRepository;
import com.thilina271.AI.powered.Quiz.app.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<UserDTO> register(AuthRequestDTO authRequest) {
        boolean isUserExists = userRepository.findByEmail(authRequest.getEmail()).isPresent();

        if(isUserExists){
            throw new BadRequestException("User already exists with this email id");
        }

        User user = new User();
        user.setName(authRequest.getName());
        user.setEmail(authRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));

        User newUser = userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());

        ResponseCookie cookie = ResponseCookie.from("token", token)
                .httpOnly(true)
                .secure(false)  // change to true on production (HTTPS)
                .path("/")
                .maxAge(60 * 60 * 24 * 7)
                .sameSite("Lax")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(UserMapper.toDTO(newUser));

    }

    public ResponseEntity<UserDTO> login(AuthRequestDTO authRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        String token = jwtUtil.generateToken(authRequest.getEmail());

        User user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));

        ResponseCookie cookie = ResponseCookie.from("token", token)
                .httpOnly(true)
                .secure(false)  // change to true on production (HTTPS)
                .path("/")
                .maxAge(60 * 60 * 24 * 7)
                .sameSite("Lax")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(UserMapper.toDTO(user));
    }

    public ResponseEntity<Void> logout() {
        // Clear the JWT cookie by setting maxAge to 0
        ResponseCookie cookie = ResponseCookie.from("token", "")
                .httpOnly(true)
                .secure(false) // set to true in production
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }

    public ResponseEntity<UserDTO> getMe(){
        // Fetch current logged-in user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(()->new ResourceNotFoundException("User not found"));

        UserDTO userDTO = UserMapper.toDTO(user);

        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }


}
