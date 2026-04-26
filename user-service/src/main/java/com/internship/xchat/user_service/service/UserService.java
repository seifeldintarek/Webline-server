package com.internship.xchat.user_service.service;

import com.internship.xchat.user_service.dto.UserDTO;
import com.internship.xchat.user_service.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    public void delete(Long id);

    public User getUser(Long id);

    public Page<User> getAllUsers(Pageable page);

    public void deleteAllUsers();

    public User update(UserDTO userDTO, Long id);

    public Page<User> search(String name, String email, String number, Pageable page);

}
