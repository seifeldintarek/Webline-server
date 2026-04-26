package com.internship.xchat.user_service.controller;

import com.internship.xchat.common_lib.security.JwtTokenProvider;
import com.internship.xchat.user_service.dto.UserDTO;
import com.internship.xchat.user_service.entity.User;
import com.internship.xchat.user_service.service.AuthService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "${app.api.auth}")
public class AuthController {

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;

    public AuthController(AuthService authService){ this.authService = authService;}

    @PostMapping(path = "${app.api.signup}")
    public User register(@Valid @RequestBody UserDTO userDTO) {
        User user = this.authService.register(userDTO);
        return user;
    }

    @PostMapping(path = "${app.api.login}")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody UserDTO userDTO) {
         User user = this.authService.login(userDTO.getEmail(), userDTO.getPassword());
             Map<String, Object> userClaims = new HashMap<>();
             userClaims.put("id", user.getId());
             userClaims.put("email", user.getEmail());
             userClaims.put("firstName", user.getFirstName());
             userClaims.put("lastName", user.getLastName());
             userClaims.put("mobileNo", user.getMobilePhone());
            String token = this.jwtTokenProvider.generateToken(userClaims, user.getEmail());

            Map<String, String> response = new HashMap<>();
            response.put("access_token", token);
       return  ResponseEntity.ok().body(response);
    }
}
