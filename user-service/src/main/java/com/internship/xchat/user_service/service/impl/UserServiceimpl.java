package com.internship.xchat.user_service.service.impl;

import com.internship.xchat.user_service.dto.UserDTO;
import com.internship.xchat.user_service.entity.User;
import com.internship.xchat.common_lib.exception.*;
import com.internship.xchat.user_service.repository.UserRepository;
import com.internship.xchat.user_service.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.*;

import java.util.Optional;

@Service
public class UserServiceimpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceimpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private String HashPass(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public User getUser(Long id) {
        return this.userRepository.findById(id).get();
    }


    @Override
    public Page<User> getAllUsers(Pageable page) {
        return this.userRepository.findAll(page);
    }

    @Override
    public void deleteAllUsers() {
        this.userRepository.deleteAll();
    }


    @Override
    public User update(UserDTO userDTO, Long id) {

        User updatedUser = this.userRepository.findById(id).get();

        if (userDTO.getEmail() != null) {
            if (!userDTO.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                throw new BadRequestException("Invalid email format");
            }

            Optional<User> optionalUser = this.userRepository.findUserByEmail(userDTO.getEmail());
            if (optionalUser.isPresent() && !optionalUser.get().getEmail().equals(updatedUser.getEmail())) {
                throw new BadRequestException("Email is already taken");
            }

            updatedUser.setEmail(userDTO.getEmail());
        }

        if (userDTO.getPassword() != null) {
            String hashedPassword = HashPass(userDTO.getPassword());
            updatedUser.setPassword(hashedPassword);
        }

        if (userDTO.getMobilePhone() != null) {
            updatedUser.setMobilePhone(userDTO.getMobilePhone());
        }

        if (userDTO.getFirstName() != null) {
            updatedUser.setFirstName(userDTO.getFirstName());
        }

        if (userDTO.getLastName() != null) {
            updatedUser.setLastName(userDTO.getLastName());
        }

        return this.userRepository.save(updatedUser);
    }

    @Override
    public Page<User> search(String name, String email, String number, Pageable page) {
        Page<User> response = this.userRepository.search(name, email, number, page);
        return response == null ? Page.empty() : response;
    }

}
