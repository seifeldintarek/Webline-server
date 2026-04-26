package com.internship.xchat.user_service.service.impl;

import com.internship.xchat.user_service.dto.UserDTO;
import com.internship.xchat.user_service.entity.User;
import com.internship.xchat.common_lib.exception.BadRequestException;
import com.internship.xchat.common_lib.exception.ResourceNotFoundException;
import com.internship.xchat.user_service.mapper.UserMapper;
import com.internship.xchat.user_service.repository.UserRepository;
import com.internship.xchat.user_service.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.*;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FileServiceImpl fileConverter;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, FileServiceImpl fileConverter) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.fileConverter = fileConverter;
    }

    private String HashPass(String password) {
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public  User login(String email, String password) {
        //Email check
        Optional<User> optionalUser = this.userRepository.findUserByEmail(email);

        if (!optionalUser.isPresent())
            throw new ResourceNotFoundException("Email");

        boolean matched = this.passwordEncoder.matches(password, optionalUser.get().getPassword());

        if (!matched) {
            throw new BadRequestException("Login Failed");
        }

        return optionalUser.orElse(null);
    }

    @Override
    public User register(UserDTO userDTO) {


        //Email check
        Optional<User> optionalUser = this.userRepository.findUserByEmail(userDTO.getEmail());
        if (optionalUser.isPresent())
            throw new BadRequestException("Email is already taken");

        // Password Hashing
        String hashedPassword = HashPass(userDTO.getPassword());
        userDTO.setPassword(hashedPassword);

        User user = UserMapper.toEntity(userDTO);
        

        this.userRepository.save(user);
        return user;
    }

}
