package com.internship.xchat.user_service.mapper;

import com.internship.xchat.user_service.dto.UserDTO;
import com.internship.xchat.user_service.entity.User;

public class UserMapper {


    public static User toEntity(UserDTO userDTO)
    {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName().trim());
        user.setLastName(userDTO.getLastName().trim());
        user.setEmail(userDTO.getEmail().trim());
        user.setPassword(userDTO.getPassword().trim());
        user.setMobilePhone(userDTO.getMobilePhone());
        return user;
    }

    public static UserDTO toDTO(User user) {
        if (user == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword()); // optional, maybe exclude for security
        dto.setMobilePhone(user.getMobilePhone());
        dto.setImage(user.getImage());
        return dto;
    }



}
