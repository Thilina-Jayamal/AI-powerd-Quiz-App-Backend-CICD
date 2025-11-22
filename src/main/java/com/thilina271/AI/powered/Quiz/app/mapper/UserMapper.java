package com.thilina271.AI.powered.Quiz.app.mapper;

import com.thilina271.AI.powered.Quiz.app.dto.UserDTO;
import com.thilina271.AI.powered.Quiz.app.model.User;

public class UserMapper {
    public static UserDTO toDTO(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }
}
