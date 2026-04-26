package com.internship.xchat.user_service.service;

import com.internship.xchat.user_service.dto.UserDTO;
import com.internship.xchat.user_service.entity.User;


public interface AuthService {

    public User login(String email, String password);
    public User register(UserDTO userDTO);

}
