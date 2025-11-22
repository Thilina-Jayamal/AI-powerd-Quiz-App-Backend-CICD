package com.thilina271.AI.powered.Quiz.app.dto;

import com.thilina271.AI.powered.Quiz.app.validation.LoginGroup;
import com.thilina271.AI.powered.Quiz.app.validation.RegisterGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthRequestDTO {
    // For REGISTER only
    @NotBlank(message = "Name is required", groups = RegisterGroup.class)
    private String name;

    // For LOGIN + REGISTER
    @NotBlank(message = "Email is required", groups = {LoginGroup.class, RegisterGroup.class})
    @Email(message = "Invalid email format", groups = {LoginGroup.class, RegisterGroup.class})
    private String email;

    // For LOGIN + REGISTER, but password length only required for register
    @NotBlank(message = "Password is required", groups = {LoginGroup.class, RegisterGroup.class})
    @Size(min = 8, message = "Password must be at least 8 characters", groups = RegisterGroup.class)
    private String password;

}
